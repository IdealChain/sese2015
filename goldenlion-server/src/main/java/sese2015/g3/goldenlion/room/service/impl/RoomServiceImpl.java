package sese2015.g3.goldenlion.room.service.impl;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sese2015.g3.goldenlion.room.domain.Room;
import sese2015.g3.goldenlion.room.repository.RoomRepository;

import java.util.Date;
import java.util.List;

@Service
public class RoomServiceImpl implements sese2015.g3.goldenlion.room.service.RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> getAllRooms() {
        return IteratorUtils.toList(roomRepository.findAll().iterator());
    }

    @Override
    public List<Room> getFreeRooms(Date fromDate, Date toDate) {
        return IteratorUtils.toList(roomRepository.findFreeRooms(fromDate, toDate).iterator());
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findOne(id);
    }
}
