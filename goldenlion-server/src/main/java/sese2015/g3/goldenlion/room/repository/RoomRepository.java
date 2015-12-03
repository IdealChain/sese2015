package sese2015.g3.goldenlion.room.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sese2015.g3.goldenlion.room.domain.Room;

import java.util.Date;

@SuppressWarnings("ALL")
@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    @Query("select room from Room as room " +
            "where not exists (" +
                "select r from Reservation as r join r.rooms room2 where room2.id = room.id " +
                "and (" +
                    "(r.startDate >= ?1 and r.startDate < ?2) or" +
                    "(r.endDate > ?1 and r.endDate <= ?2) or" +
                    "(r.startDate < ?1 and r.endDate > ?2)" +
                ")" +
            ")")
    Iterable<Room> findFreeRooms(Date fromDate, Date toDate);
}
