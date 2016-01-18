package sese2015.g3.goldenlion.hotel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sese2015.g3.goldenlion.customer.domain.Address;
import sese2015.g3.goldenlion.hotel.domain.Hotel;
import sese2015.g3.goldenlion.hotel.repository.HotelRepository;
import sese2015.g3.goldenlion.hotel.service.HotelService;

import javax.annotation.PostConstruct;
import java.util.Iterator;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel getHotelData() {
        Iterator<Hotel> it = hotelRepository.findAll().iterator();

        if (!it.hasNext())
            return null;

        return it.next();
    }

    @PostConstruct
    private void initHotelData() {
        Hotel hotel = new Hotel();
        hotel.setName("Hotel zum Goldenen Löwen");
        hotel.setLogo("/assets/images/goldenlion.png");

        Address addr = new Address();
        addr.setStreet("Kärnter Ring");
        addr.setStreetNumber("16");
        addr.setZipCode("1010");
        addr.setCity("Wien");
        addr.setCountry("Österreich");
        hotel.setAddress(addr);

        hotelRepository.save(hotel);
    }
}
