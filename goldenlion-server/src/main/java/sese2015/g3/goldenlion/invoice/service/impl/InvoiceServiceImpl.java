package sese2015.g3.goldenlion.invoice.service.impl;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sese2015.g3.goldenlion.invoice.domain.Invoice;
import sese2015.g3.goldenlion.invoice.repository.InvoiceRepository;
import sese2015.g3.goldenlion.invoice.service.InvoiceService;
import sese2015.g3.goldenlion.reservation.domain.Reservation;
import sese2015.g3.goldenlion.reservation.repository.ReservationRepository;
import sese2015.g3.goldenlion.reservation.service.impl.ReservationNotFoundException;

import java.util.Date;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice createInvoice(Long reservationId) {

        Invoice invoice = new Invoice();
        invoice.setInvoiceDate(new Date());
        Reservation reservation = reservationRepository.findOne(reservationId);

        if(reservation == null) {
            throw new ReservationNotFoundException("Reservation not found: " + reservationId);
        }

        invoice.setReservation(reservationRepository.findOne(reservationId));

        invoiceRepository.save(invoice);

        return invoice;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return IteratorUtils.toList(invoiceRepository.findAll().iterator());
    }
}
