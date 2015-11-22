package sese2015.g3.goldenlion.customer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sese2015.g3.goldenlion.customer.domain.Address;

/**
 * Created by Mario on 21.11.2015.
 */
@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
}
