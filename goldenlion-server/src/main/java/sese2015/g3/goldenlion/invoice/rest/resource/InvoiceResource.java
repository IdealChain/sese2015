package sese2015.g3.goldenlion.invoice.rest.resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sese2015.g3.goldenlion.invoice.domain.Invoice;
import sese2015.g3.goldenlion.invoice.service.InvoiceService;
import sese2015.g3.goldenlion.reservation.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class InvoiceResource {
    private Log log = LogFactory.getLog(getClass());

    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(value = "/invoice", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Invoice createInvoice(@RequestParam(value="reservationid", required = true) Long reservationId) {
        log.info("Create invoice ...");

        Invoice invoice = invoiceService.createInvoice(reservationId);

        log.info("Invoice creation successful...");

        return invoice;
    }

    @RequestMapping(value = "/invoice",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }
}
