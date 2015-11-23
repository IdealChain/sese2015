package sese2015.g3.goldenlion.customer.service;

import sese2015.g3.goldenlion.customer.domain.Customer;
import sese2015.g3.goldenlion.customer.dto.CustomerDto;

import java.util.List;

/**
 * Created by Mario on 21.11.2015.
 * Updated by Fabian on 23.11.2015.
 */
public interface CustomerService {
    void createCustomer(CustomerDto customerDto);
    List<Customer> getAllCustomers();
}
