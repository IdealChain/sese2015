package sese2015.g3.goldenlion.hotel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sese2015.g3.goldenlion.hotel.domain.Hotel;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {
}
