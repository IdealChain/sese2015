package sese2015.g3.goldenlion.invoice.service;

import sese2015.g3.goldenlion.invoice.domain.Invoice;
import sese2015.g3.goldenlion.reservation.domain.Reservation;

public interface InvoiceService {

    Invoice createInvoice(Long reservationId);
}
