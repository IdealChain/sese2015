package sese2015.g3.goldenlion.room.service;

import sese2015.g3.goldenlion.room.domain.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();

    Room getRoomById(Long id);
}
