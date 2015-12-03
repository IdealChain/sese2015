package sese2015.g3.goldenlion.room.service;

import sese2015.g3.goldenlion.room.domain.Room;

import java.util.Date;
import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();

    List<Room> getFreeRooms(Date fromDate, Date toDate);

    Room getRoomById(Long id);
}
