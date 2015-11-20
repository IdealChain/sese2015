package sese2015.g3.goldenlion.reservation.service;

import sese2015.g3.goldenlion.reservation.rest.domain.request.CreateReservationRequest;

public interface ReservationService {
    long createReservation(CreateReservationRequest request);
}
