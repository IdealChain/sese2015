package sese2015.g3.goldenlion.room.rest.resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sese2015.g3.goldenlion.commons.service.impl.ResourceNotFoundException;
import sese2015.g3.goldenlion.room.domain.Room;
import sese2015.g3.goldenlion.room.service.RoomService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
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
            @RequestParam(value = "freefrom", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date freeFromDate,
            @RequestParam(value = "freeto", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date freeToDate,
            @RequestParam(value = "maxPersons", required = false) Integer maxPersons) {


        if (freeFromDate != null && freeToDate != null) {
            List<Room> freeRooms = roomService.getFreeRooms(freeFromDate, freeToDate);
            return freeRooms.stream().filter(room -> maxPersons != null ? room.getMaxPersons() >= maxPersons : true).collect(Collectors.toList());
        } else {
            return roomService.getAllRooms();
        }
    }

    @RequestMapping(
            value = "/rooms/{roomid}/rate",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public double getRoomRate(
            @PathVariable Long roomid,
            @RequestParam(value = "numberOfAdults", defaultValue = "0") int numberOfAdults,
            @RequestParam(value = "numberOfChildren", defaultValue = "0") int numberOfChildren) {

        Room room = roomService.getRoomById(roomid);
        if (room == null)
            throw new ResourceNotFoundException(String.format("Room with given room-id %s not found", roomid));

        return room.getRate(numberOfAdults, numberOfChildren);
    }
}
