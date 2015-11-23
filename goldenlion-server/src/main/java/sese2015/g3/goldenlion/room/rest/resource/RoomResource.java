package sese2015.g3.goldenlion.room.rest.resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sese2015.g3.goldenlion.commons.service.impl.ResourceNotFoundException;
import sese2015.g3.goldenlion.room.domain.Room;
import sese2015.g3.goldenlion.room.repository.RoomRepository;
import sese2015.g3.goldenlion.room.service.RoomService;

@RestController
@RequestMapping(value = "/api")
public class RoomResource {
    private Log log = LogFactory.getLog(getClass());

    @Autowired
    private RoomService roomService;

    @RequestMapping(
            value = "/room/{roomid}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Room getRoomById(@PathVariable Long roomid) {
        Room room = roomService.getRoomById(roomid);
        if (room == null)
            throw new ResourceNotFoundException(String.format("Room with given room-id %s not found", roomid));
        return room;
    }
}
