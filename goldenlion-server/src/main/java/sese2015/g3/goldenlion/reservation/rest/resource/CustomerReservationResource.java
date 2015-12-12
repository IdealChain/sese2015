package sese2015.g3.goldenlion.reservation.rest.resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sese2015.g3.goldenlion.customer.dto.CustomerDto;
import sese2015.g3.goldenlion.customer.service.CustomerService;
import sese2015.g3.goldenlion.reservation.mapper.CustomerMapper;
import sese2015.g3.goldenlion.reservation.rest.domain.request.CreateCustomerReservationRequest;
import sese2015.g3.goldenlion.reservation.rest.domain.request.CreateReservationRequest;
import sese2015.g3.goldenlion.reservation.rest.domain.response.CreateReservationResponse;
import sese2015.g3.goldenlion.reservation.service.ReservationService;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * Created by Mario on 08.12.2015.
 */
@RestController
@RequestMapping(value = "/api/customerReservation")
public class CustomerReservationResource {
    private Log log = LogFactory.getLog(getClass());

    private ReservationService reservationService;
    private CustomerService customerService;

    @Autowired
    public CustomerReservationResource(ReservationService reservationService, CustomerService customerService) {
        this.reservationService = reservationService;
        this.customerService = customerService;
    }

    @RequestMapping(
            method = RequestMethod.POST)
    public CreateReservationResponse createCustomerReservation(@Valid @RequestBody CreateCustomerReservationRequest request) {
        log.info("Incoming Create Customer Reservation Request... ");

        CustomerDto searchedCustomer = this.customerService.getCustomerByEmail(request.getCustomer().getEmail());
        if (searchedCustomer == null) {
            log.info("Mapping reservationCustoemr to customerDto!");
            searchedCustomer = CustomerMapper.INSTANCE.reservationCustomerToCustomerDto(request.getCustomer());
            this.customerService.createCustomer(searchedCustomer);
            log.info("Mapping successfull!");
        }


        log.info("Creating request for customer with id: " + searchedCustomer.getId());
        CreateReservationRequest createReservationRequest = new CreateReservationRequest();
        createReservationRequest.setRoomId(request.getRoomId());
        createReservationRequest.setStartDate(request.getStartDate());
        createReservationRequest.setEndDate(request.getEndDate());
        createReservationRequest.setCustomerIds(Arrays.asList(searchedCustomer.getId()));

        long reservationId = reservationService.createReservation(createReservationRequest);
        log.info("Success");

        return new CreateReservationResponse(reservationId);
    }
}
