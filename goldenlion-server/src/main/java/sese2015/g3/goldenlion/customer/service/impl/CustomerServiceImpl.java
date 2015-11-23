package sese2015.g3.goldenlion.customer.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sese2015.g3.goldenlion.customer.domain.Address;
import sese2015.g3.goldenlion.customer.domain.Customer;
import sese2015.g3.goldenlion.customer.dto.CustomerDto;
import sese2015.g3.goldenlion.customer.repository.AddressRepository;
import sese2015.g3.goldenlion.customer.repository.CustomerRepository;
import sese2015.g3.goldenlion.customer.service.CustomerService;

import java.time.ZoneId;
import java.util.Date;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Mario on 21.11.2015.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    private AddressRepository addressRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(AddressRepository addressRepository, CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void createCustomer(CustomerDto customerCreateRequest) {
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

        this.customerRepository.save(customer);
    }

    private boolean doesCustomerExist(String email) {
        checkNotNull(email);
        checkArgument(StringUtils.isNotBlank(email), "Email address must not be null or blank!");
        Customer searchedCustomer = this.customerRepository.findByEmail(email);
        return searchedCustomer != null ? true : false;
    }
}
