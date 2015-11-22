package sese2015.g3.goldenlion.room.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sese2015.g3.goldenlion.room.domain.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    Room findOne(Long id);

    Iterable<Room> findAll();
}
