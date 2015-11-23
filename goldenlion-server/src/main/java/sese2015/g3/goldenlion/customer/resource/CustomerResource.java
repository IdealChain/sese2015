package sese2015.g3.goldenlion.customer.resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sese2015.g3.goldenlion.customer.domain.Customer;
import sese2015.g3.goldenlion.customer.dto.CustomerDto;
import sese2015.g3.goldenlion.customer.service.CustomerService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Mario on 21.11.2015.
 */
@RestController
@RequestMapping(value = "/api/customers")
public class CustomerResource {
    private Log log = LogFactory.getLog(getClass());
    private CustomerService customerService;

    @Autowired
    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> list() {
        return new ResponseEntity<List<Customer>>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable Long id) {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADM')")
    public ResponseEntity<CustomerDto> create(@RequestBody @Valid CustomerDto dto, BindingResult result) {
        CustomerDto response = new CustomerDto();
        this.customerService.createCustomer(dto);
        return new ResponseEntity<CustomerDto>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<CustomerDto> update(@PathVariable Long id, @Valid @RequestBody CustomerDto dto, BindingResult result) {
        return null;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<CustomerDto> delete(@PathVariable Long id) {
        return null;
    }
}
