package sese2015.g3.goldenlion.invoice.domain;

import sese2015.g3.goldenlion.commons.entity.PersistentObject;
import sese2015.g3.goldenlion.customer.domain.Customer;
import sese2015.g3.goldenlion.reservation.domain.Reservation;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "invoice")
public class Invoice extends PersistentObject {

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @Column
    @Temporal(TemporalType.DATE)
    private Date invoiceDate;

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    //Anschrift Hotel!?
}
