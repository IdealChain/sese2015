package sese2015.g3.goldenlion.invoice.service;

import sese2015.g3.goldenlion.hotel.domain.Hotel;
import sese2015.g3.goldenlion.invoice.domain.Invoice;

import java.util.List;

public interface InvoiceService {

    Invoice createInvoice(Long reservationId, Long billedCustomerId);

    Invoice getInvoice(Long reservationId);

    void invalidateInvoice(Long reservationId);

    List<Invoice> getAllInvoices(boolean includeInvalidated);
}
