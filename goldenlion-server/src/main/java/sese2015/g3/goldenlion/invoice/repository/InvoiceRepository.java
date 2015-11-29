package sese2015.g3.goldenlion.invoice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sese2015.g3.goldenlion.invoice.domain.Invoice;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

}
