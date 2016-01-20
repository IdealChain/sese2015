package sese2015.g3.goldenlion.invoice.service.impl;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sese2015.g3.goldenlion.customer.domain.Address;
import sese2015.g3.goldenlion.customer.domain.Customer;
import sese2015.g3.goldenlion.hotel.domain.Hotel;
import sese2015.g3.goldenlion.invoice.domain.Invoice;
import sese2015.g3.goldenlion.hotel.repository.HotelRepository;
import sese2015.g3.goldenlion.invoice.repository.InvoiceRepository;
import sese2015.g3.goldenlion.invoice.service.InvoiceService;
import sese2015.g3.goldenlion.reservation.domain.Reservation;
import sese2015.g3.goldenlion.reservation.repository.ReservationRepository;
import sese2015.g3.goldenlion.reservation.service.impl.ReservationNotFoundException;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Invoice createInvoice(Long reservationId, Long billedCustomerId) {

        Reservation reservation = reservationRepository.findOne(reservationId);
        Optional<Customer> billedCustomer = reservation.getCustomers().stream().filter(cust -> cust.getId().equals(billedCustomerId)).findFirst();

        if(reservation == null) {
            throw new ReservationNotFoundException("Reservation not found: " + reservationId);
        } else if (!billedCustomer.isPresent()) {
            throw new ReservationNotFoundException(String.format("Customer %d is not part of given reservation %d", billedCustomerId, reservationId));
        }

        Invoice invoice = new Invoice(reservation, billedCustomer.get());
        invoiceRepository.save(invoice);

        return invoice;
    }

    @Override
    public Invoice getInvoice(Long reservationId) {
        return invoiceRepository.findOne(reservationId);
    }

    @Override
    public void invalidateInvoice(Long reservationId) {
        Invoice invoice = invoiceRepository.findOne(reservationId);

        if (invoice == null) {
            throw new ReservationNotFoundException("Invoice not found: " + reservationId);
        }

        invoice.setInvalidated(true);
        invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> getAllInvoices(boolean includeInvalidated) {
        if (includeInvalidated)
            return IteratorUtils.toList(invoiceRepository.findAllByOrderByInvoiceDateDesc().iterator());
        else
            return IteratorUtils.toList(invoiceRepository.findValid().iterator());
    }
}
