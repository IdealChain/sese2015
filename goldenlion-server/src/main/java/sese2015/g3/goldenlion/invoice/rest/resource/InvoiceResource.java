package sese2015.g3.goldenlion.invoice.rest.resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sese2015.g3.goldenlion.commons.service.impl.ResourceInvalidatedException;
import sese2015.g3.goldenlion.commons.service.impl.ResourceNotFoundException;
import sese2015.g3.goldenlion.hotel.domain.Hotel;
import sese2015.g3.goldenlion.invoice.domain.Invoice;
import sese2015.g3.goldenlion.invoice.service.InvoiceService;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@PreAuthorize("hasAnyAuthority('ADM','EMP')")
public class InvoiceResource {
    private Log log = LogFactory.getLog(getClass());

    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(value = "/invoice", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Invoice createInvoice(
            @RequestParam(value="reservationid", required = true) Long reservationId,
            @RequestParam(value="billedcustomerid", required = true) Long billedCustomerId) {
        log.info("Create invoice ...");

        Invoice invoice = invoiceService.createInvoice(reservationId, billedCustomerId);

        log.info("Invoice creation successful...");

        return invoice;
    }

    @RequestMapping(value = "/invoice/{reservationId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Invoice getInvoice(@PathVariable Long reservationId) {
        Invoice invoice = invoiceService.getInvoice(reservationId);

        if (invoice == null)
            throw new ResourceNotFoundException(String.format("No invoice for reservation-id %d found", reservationId));

        if (invoice.isInvalidated())
            throw new ResourceInvalidatedException(String.format("The invoice %d was invalidated and is not available anymore", reservationId));

        return invoice;
    }

    @RequestMapping(value = "/invoice/{reservationId}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public void invalidateInvoice(@PathVariable Long reservationId) {
        invoiceService.invalidateInvoice(reservationId);
        log.info("Invalidated invoice " + reservationId);
    }

    @RequestMapping(value = "/invoice",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Invoice> getAllInvoices(@RequestParam(value="includeInvalidated", required = false) Boolean includeInvalidated) {
        if (includeInvalidated != null)
            return invoiceService.getAllInvoices(includeInvalidated);
        else
            return invoiceService.getAllInvoices(false);
    }
}
