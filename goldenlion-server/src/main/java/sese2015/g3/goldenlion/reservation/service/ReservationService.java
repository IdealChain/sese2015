package sese2015.g3.goldenlion.reservation.service;

import sese2015.g3.goldenlion.reservation.rest.domain.request.CreateReservationRequest;
import sese2015.g3.goldenlion.reservation.service.impl.CreateReservationFailedException;

public interface ReservationService {
    long createReservation(CreateReservationRequest request) throws CreateReservationFailedException;
}
