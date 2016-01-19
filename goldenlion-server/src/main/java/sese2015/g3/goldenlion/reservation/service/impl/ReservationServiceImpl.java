package sese2015.g3.goldenlion.reservation.service.impl;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sese2015.g3.goldenlion.customer.domain.Customer;
import sese2015.g3.goldenlion.customer.repository.CustomerRepository;
import sese2015.g3.goldenlion.invoice.domain.Invoice;
import sese2015.g3.goldenlion.invoice.repository.InvoiceRepository;
import sese2015.g3.goldenlion.reservation.domain.Reservation;
import sese2015.g3.goldenlion.reservation.repository.ReservationRepository;
import sese2015.g3.goldenlion.reservation.rest.domain.request.CreateReservationRequest;
import sese2015.g3.goldenlion.reservation.service.ReservationService;
import sese2015.g3.goldenlion.room.domain.Room;
import sese2015.g3.goldenlion.room.repository.RoomRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    //TODO: Transaction
    @Override
    public long createReservation(CreateReservationRequest request) throws BadInputDataException {
        Room room = roomRepository.findOne(request.getRoomId());
        List<Customer> customers = IteratorUtils.toList(customerRepository.findAll(request.getCustomerIds()).iterator());

        //
        // basic input validation
        if (room == null)
            throw new BadInputDataException(String.format("Given Room-Id %s not found", request.getRoomId()));
        if (customers == null || customers.size() != request.getCustomerIds().size())
            throw new BadInputDataException(
                    String.format("Given number of customers (%s) does not match found number of customers (%s)",
                            request.getCustomerIds().size(), customers.size()));
        if (customers.size() == 0)
            throw new BadInputDataException("Reservation must at least contain one customer");
        if (request.getStartDate() == null || request.getEndDate() == null)
            throw new BadInputDataException("Start date or end date is null");
        if (request.getStartDate().after(request.getEndDate()) || request.getStartDate().equals(request.getEndDate()))
            throw new BadInputDataException("End date must be at least 1 day after the start date");

        //
        // check if the room free within the given timespan
        List<Reservation> conflictingReservations = IteratorUtils.toList(reservationRepository.findOverlappingReservations(
                request.getStartDate(),
                request.getEndDate(),
                room.getId()).iterator());
        if (conflictingReservations.size() > 0)
            throw new ConflictingDataException(String.format("Found %s conflicting reservations between %s and %s with Room-Id %s",
                    conflictingReservations.size(), request.getStartDate(), request.getEndDate(), room.getId()));

        //
        // total number of adults must be greater or equal than number of customers
        if(customers.size() > request.getNumberOfAdults())
            throw new BadInputDataException(String.format("Total number of adults %s must be greater or equal than number of customers %s",
                    request.getNumberOfAdults(), customers.size()));

        //
        // check if the room has the capacity for the requested amount of people
        if(request.getNumberOfAdults() + request.getNumberOfChildren() > room.getMaxPersons())
            throw new RoomCapacityExceededException(String.format("%d persons exceed the room capacity of %d",
                    customers.size() + request.getNumberOfChildren(), room.getMaxPersons()));

        //
        // everything seems fine. Save Reservation
        Reservation r = new Reservation();
        r.setStartDate(request.getStartDate());
        r.setEndDate(request.getEndDate());
        r.setRooms(new ArrayList<Room>() {{
            add(room);
        }});
        r.setCustomers(customers);
        r.setNumberOfAdults(request.getNumberOfAdults());
        r.setNumberOfChildren(request.getNumberOfChildren());

        return reservationRepository.save(r).getId();
    }

    @Override
    public boolean isAvailable(Long roomId, Date startDate, Date endDate) {
        return (!reservationRepository.findOverlappingReservations(startDate, endDate, roomId).iterator().hasNext());
    }

    @Override
    public List<Reservation> getAllReservations() {
        return IteratorUtils.toList(reservationRepository.findAll(new Sort(Sort.Direction.DESC, "startDate")).iterator());
    }

    @Override
    public List<Reservation> getAllReservationsWithoutInvoiceByCustomer(Long customerid) {
        return IteratorUtils.toList(reservationRepository.findByCustomerIdWithoutInvoice(customerid).iterator());
    }

    @Override
    public List<Reservation> getAllReservationsOfRoom(Long roomid, Date startDate, Date endDate) {
        if(roomid == null)
            throw new BadInputDataException("Room-ID is null");

        if(startDate != null && endDate != null)
            return IteratorUtils.toList(reservationRepository.findOverlappingReservations(startDate, endDate, roomid).iterator());
        else
            return IteratorUtils.toList(reservationRepository.findByRoomId(roomid).iterator());
    }

    @Override
    public void cancelReservation(Long reservationid) {
        Reservation reservation = reservationRepository.findOne(reservationid);
        if (reservation == null)
            throw new ReservationNotFoundException("The given reservation-id was not found");

        Invoice invoice = invoiceRepository.findByReservationId(reservationid);
        if (invoice != null)
            throw new ConflictingDataException("Cannot delete reservation which is already billed");

        reservationRepository.delete(reservation.getId());
    }
}
