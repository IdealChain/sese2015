package sese2015.g3.goldenlion.reservation.service;

import sese2015.g3.goldenlion.reservation.domain.Reservation;
import sese2015.g3.goldenlion.reservation.rest.domain.request.CreateReservationRequest;
import sese2015.g3.goldenlion.reservation.service.impl.BadInputDataException;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    long createReservation(CreateReservationRequest request) throws BadInputDataException;

    boolean isAvailable(Long roomId, Date startDate, Date endDate);

    List<Reservation> getAllReservations();

    List<Reservation> getAllReservationsWithoutInvoiceByCustomer(Long customerid);

    void cancelReservation(Long reservationid);
}
