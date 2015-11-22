package sese2015.g3.goldenlion.customer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sese2015.g3.goldenlion.customer.domain.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    public Customer findByEmail(String email);
}
