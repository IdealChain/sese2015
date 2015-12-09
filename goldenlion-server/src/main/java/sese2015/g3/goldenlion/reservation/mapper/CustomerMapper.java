package sese2015.g3.goldenlion.reservation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import sese2015.g3.goldenlion.customer.domain.Customer;
import sese2015.g3.goldenlion.customer.dto.CustomerDto;
import sese2015.g3.goldenlion.reservation.rest.domain.request.ReservationCustomer;

/**
 * Created by Mario on 09.12.2015.
 */
@Mapper(uses = DateMapper.class)
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto reservationCustomerToCustomerDto(ReservationCustomer reservationCustomer);

    CustomerDto customerToCustomerDto(Customer customer);
}
