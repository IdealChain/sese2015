package sese2015.g3.goldenlion.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sese2015.g3.goldenlion.reservation.domain.Reservation;
import sese2015.g3.goldenlion.reservation.repository.ReservationRepository;
import sese2015.g3.goldenlion.reservation.rest.domain.request.CreateReservationRequest;
import sese2015.g3.goldenlion.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public long createReservation(CreateReservationRequest request) {
        Reservation r = new Reservation();
        r.setStartDate(request.getStartDate());
        r.setEndDate(request.getEndDate());

        Reservation rSaved = reservationRepository.save(r);
        return rSaved.getId();
    }
}
