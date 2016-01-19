package sese2015.g3.goldenlion.customer.service.impl;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sese2015.g3.goldenlion.customer.domain.Address;
import sese2015.g3.goldenlion.customer.domain.Customer;
import sese2015.g3.goldenlion.customer.dto.CustomerDto;
import sese2015.g3.goldenlion.customer.repository.CustomerRepository;
import sese2015.g3.goldenlion.customer.service.CustomerService;
import sese2015.g3.goldenlion.reservation.mapper.CustomerMapper;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Mario on 21.11.2015.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return IteratorUtils.toList(customerRepository.findAll().iterator());
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerToUpdate) {
        checkNotNull(customerToUpdate, "CustomerToUpdate must not be null!");
        Customer currentCustomer = this.customerRepository.findOne(customerToUpdate.getId());

        if (currentCustomer == null) {
            throw new IllegalStateException("Customer not found!");
        }

        Address currentCustomerAddress = currentCustomer.getBillingAddress();
        currentCustomerAddress.setCity(customerToUpdate.getCity());
        currentCustomerAddress.setCountry(customerToUpdate.getState());
        currentCustomerAddress.setStreet(customerToUpdate.getStreet());
        currentCustomerAddress.setStreetNumber(customerToUpdate.getStreetExtension());
        currentCustomerAddress.setZipCode(String.valueOf(customerToUpdate.getPostalCode()));

        currentCustomer.setFirstName(customerToUpdate.getFirstName());
        currentCustomer.setLastName(customerToUpdate.getLastName());
        currentCustomer.setBirthday(Date.from(customerToUpdate.getBirthday().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        currentCustomer.setCompanyName(customerToUpdate.getCompanyName());
        currentCustomer.setDiscount(customerToUpdate.getDiscount());
        currentCustomer.setEmail(customerToUpdate.getEmail());
        currentCustomer.setFaxNumber(customerToUpdate.getFax());
        currentCustomer.setGender(customerToUpdate.getGender());
        currentCustomer.setNote(customerToUpdate.getNotes());
        currentCustomer.setPhoneNumber(customerToUpdate.getTelephone());
        currentCustomer.setWebsite(customerToUpdate.getWeb());

        this.customerRepository.save(currentCustomer);
        return CustomerMapper.INSTANCE.customerToCustomerDto(currentCustomer);
    }

    @Override
    public long createCustomer(CustomerDto customerCreateRequest) {
        checkNotNull(customerCreateRequest, "CustomerCreateRequest must not be null!");
        checkArgument(!doesCustomerExist(customerCreateRequest.getEmail()), "Customer with this email address allready exists!");

        Address address = new Address();
        address.setCity(customerCreateRequest.getCity());
        address.setCountry(customerCreateRequest.getState());
        address.setStreet(customerCreateRequest.getStreet());
        address.setStreetNumber(customerCreateRequest.getStreetExtension());
        address.setZipCode(String.valueOf(customerCreateRequest.getPostalCode()));

        Customer customer = new Customer();
        customer.setBillingAddress(address);
        customer.setFirstName(customerCreateRequest.getFirstName());
        customer.setLastName(customerCreateRequest.getLastName());
        customer.setBirthday(Date.from(customerCreateRequest.getBirthday().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        customer.setCompanyName(customerCreateRequest.getCompanyName());
        customer.setDiscount(customerCreateRequest.getDiscount());
        customer.setEmail(customerCreateRequest.getEmail());
        customer.setFaxNumber(customerCreateRequest.getFax());
        customer.setGender(customerCreateRequest.getGender());
        customer.setNote(customerCreateRequest.getNotes());
        customer.setPhoneNumber(customerCreateRequest.getTelephone());
        customer.setWebsite(customerCreateRequest.getWeb());

        Customer savedCustomer = this.customerRepository.save(customer);
        customerCreateRequest.setId(savedCustomer.getId());
        return savedCustomer.getId();
    }

    @Override
    public CustomerDto getCustomerByEmail(String email) {
        checkNotNull(email);
        checkArgument(StringUtils.isNotBlank(email), "Email address must not be null or blank!");
        Customer searchedCustomer = this.customerRepository.findByEmail(email);
        return searchedCustomer != null ? CustomerMapper.INSTANCE.customerToCustomerDto(searchedCustomer) : null;
    }

    private boolean doesCustomerExist(String email) {
        checkNotNull(email);
        checkArgument(StringUtils.isNotBlank(email), "Email address must not be null or blank!");
        Customer searchedCustomer = this.customerRepository.findByEmail(email);
        return searchedCustomer != null ? true : false;
    }
}
