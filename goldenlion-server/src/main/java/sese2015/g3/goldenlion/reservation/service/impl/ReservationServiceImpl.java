package sese2015.g3.goldenlion.reservation.service.impl;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sese2015.g3.goldenlion.customer.domain.Customer;
import sese2015.g3.goldenlion.customer.repository.CustomerRepository;
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

    //TODO: Transaction
    @Override
    public long createReservation(CreateReservationRequest request) throws CreateReservationFailedException {
        Room room = roomRepository.findOne(request.getRoomId());
        List<Customer> customers = IteratorUtils.toList(customerRepository.findAll(request.getCustomerIds()).iterator());

        //
        // basic input validation
        if (room == null)
            throw new CreateReservationFailedException(String.format("Given Room-Id %s not found", request.getRoomId()));
        if (customers == null || customers.size() != request.getCustomerIds().size())
            throw new CreateReservationFailedException(
                    String.format("Given number of customers (%s) does not match found number of customers (%s)",
                            request.getCustomerIds().size(), customers.size()));
        if (customers.size() == 0)
            throw new CreateReservationFailedException("Reservation must at least contain one customer");
        if (request.getStartDate() == null || request.getEndDate() == null)
            throw new CreateReservationFailedException("Start date or end date is null");
        if (request.getStartDate().after(request.getEndDate()) || request.getStartDate().equals(request.getEndDate()))
            throw new CreateReservationFailedException("End date must be at least 1 day after the start date");

        //
        // check if the room free within the given timespan
        List<Reservation> conflictingReservations = IteratorUtils.toList(reservationRepository.findOverlappingReservations(
                request.getStartDate(),
                request.getEndDate(),
                room.getId()).iterator());
        if (conflictingReservations.size() > 0)
            throw new CreateReservationFailedException(String.format("Found %s conflicting reservations between %s and %s with Room-Id %s",
                    conflictingReservations.size(), request.getStartDate(), request.getEndDate(), room.getId()));

        //
        // everything seems fine. Save Reservation
        Reservation r = new Reservation();
        r.setStartDate(request.getStartDate());
        r.setEndDate(request.getEndDate());
        r.setRooms(new ArrayList<Room>() {{
            add(room);
        }});
        r.setCustomers(customers);

        return reservationRepository.save(r).getId();
    }

    @Override
    public boolean isAvailable(Long roomId, Date startDate, Date endDate) {
        return (!reservationRepository.findOverlappingReservations(startDate, endDate, roomId).iterator().hasNext());
    }
}