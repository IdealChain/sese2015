package sese2015.g3.goldenlion.invoice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sese2015.g3.goldenlion.invoice.domain.Invoice;

@SuppressWarnings("ALL")
@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

    Invoice findByReservationId(Long reservationid);

    @Query("select i from Invoice as i where i.invalidated <> true")
    Iterable<Invoice> findValid();

    Iterable<Invoice> findAllByOrderByInvoiceDateDesc();
}
