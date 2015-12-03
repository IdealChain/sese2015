package sese2015.g3.goldenlion.room.rest.resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sese2015.g3.goldenlion.commons.service.impl.ResourceNotFoundException;
import sese2015.g3.goldenlion.room.domain.Room;
import sese2015.g3.goldenlion.room.service.RoomService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@PreAuthorize("hasAnyAuthority('ADM','EMP')")
public class RoomResource {
    private Log log = LogFactory.getLog(getClass());

    @Autowired
    private RoomService roomService;

    @RequestMapping(
            value = "/rooms/{roomid}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Room getRoomById(@PathVariable Long roomid) {
        Room room = roomService.getRoomById(roomid);
        if (room == null)
            throw new ResourceNotFoundException(String.format("Room with given room-id %s not found", roomid));
        return room;
    }

    @RequestMapping(
            value = "/rooms",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Room> getRooms(
            @RequestParam(value = "freefrom", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd")Date freeFromDate,
            @RequestParam(value = "freeto", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd")Date freeToDate) {

        if (freeFromDate != null && freeToDate != null)
            return roomService.getFreeRooms(freeFromDate, freeToDate);
        else
            return roomService.getAllRooms();
    }
}
