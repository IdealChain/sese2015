package sese2015.g3.goldenlion.reservation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sese2015.g3.goldenlion.reservation.domain.Reservation;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    Reservation save(Reservation r);
}