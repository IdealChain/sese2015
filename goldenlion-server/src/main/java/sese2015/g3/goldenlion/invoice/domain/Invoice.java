package sese2015.g3.goldenlion.invoice.domain;

import sese2015.g3.goldenlion.commons.entity.PersistentObject;
import sese2015.g3.goldenlion.customer.domain.Customer;
import sese2015.g3.goldenlion.reservation.domain.Reservation;
import sese2015.g3.goldenlion.room.domain.Room;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "invoice")
public class Invoice extends PersistentObject {

    public Invoice() {
    }

    /**
     * Issues a new invoice and calculates the associated price.
     */
    public Invoice(Reservation reservation, Customer billedCustomer) {
        this.invoiceDate = new Date();
        this.reservation = reservation;
        this.billedCustomer = billedCustomer;

        Long nights = this.reservation.getNights();
        Double price = 0.0;

        for (Room room : reservation.getRooms()) {
            price += room.getPricePerNight() * nights;
        }

        setGrossPrice(price);
        if (billedCustomer.getDiscount() > 0) {
            // apply discount to net price
            setNetPrice(getNetPrice() * (1 - billedCustomer.getDiscount()));
        }
    }

    @NotNull
    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date invoiceDate;

    @Column
    private Double grossPrice;

    @Column
    private Double vatRate = 0.20;

    @NotNull
    @OneToOne
    @JoinColumn(name = "billed_customer")
    private Customer billedCustomer;

    @Column
    private boolean invalidated = false;

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

    public Double getGrossPrice() {
        return grossPrice;
    }

    public Double getVatRate() {
        return vatRate;
    }

    public void setVatRate(Double vatRate) {
        this.vatRate = vatRate;
    }

    public void setGrossPrice(Double grossPrice) {
        this.grossPrice = grossPrice;
    }

    public Customer getBilledCustomer() {
        return billedCustomer;
    }

    public void setBilledCustomer(Customer billedCustomer) {
        this.billedCustomer = billedCustomer;
    }

    public Double getNetPrice() {
        return getGrossPrice() / (1.0 + getVatRate());
    }

    public void setNetPrice(Double netPrice) {
        setGrossPrice(netPrice * (1.0 + vatRate));
    }

    public boolean isInvalidated() {
        return invalidated;
    }

    public void setInvalidated(boolean invalidated) {
        this.invalidated = invalidated;
    }
}
