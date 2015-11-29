package sese2015.g3.goldenlion.reservation.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sese2015.g3.goldenlion.reservation.domain.Reservation;
import sese2015.g3.goldenlion.room.domain.Room;

import java.util.Date;

@SuppressWarnings("ALL")
@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    @Query("select r from Reservation as r " +
            "join r.rooms room where room.id = ?3 " +
            "and (" +
            "(r.startDate >= ?1 and r.startDate < ?2) or" +
            "(r.endDate > ?1 and r.endDate <= ?2) or" +
            "(r.startDate < ?1 and r.endDate > ?2)" +
            ")")
    Iterable<Reservation> findOverlappingReservations(Date startDate, Date endDate, long roomId);

    Iterable<Reservation> findAll(Sort sort);

    @Query("select r from Reservation as r " +
            "join r.customers customer where customer.id = ?1 " +
            "order by r.startDate DESC")
    Iterable<Reservation> findByCustomerId(Long customerid);
}