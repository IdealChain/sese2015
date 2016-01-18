package sese2015.g3.goldenlion.hotel.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sese2015.g3.goldenlion.hotel.domain.Hotel;
import sese2015.g3.goldenlion.hotel.service.HotelService;

@RestController
@RequestMapping(value = "/api")
public class HotelResource {

    @Autowired
    private HotelService hotelService;

    @RequestMapping(value = "/hotel",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Hotel getHotelData() {
        return hotelService.getHotelData();
    }
}
