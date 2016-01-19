package sese2015.g3.goldenlion.reservation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import sese2015.g3.goldenlion.commons.entity.PersistentObject;
import sese2015.g3.goldenlion.customer.domain.Customer;
import sese2015.g3.goldenlion.room.domain.Room;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "reservations")
public class Reservation extends PersistentObject {

    @ManyToMany()
    @JoinTable(name = "reservation_customers", joinColumns = {@JoinColumn(name = "reservation_id")}, inverseJoinColumns = {@JoinColumn(name = "customer_id")})
    private List<Customer> customers;

    @ManyToMany()
    @JoinTable(name = "reservation_rooms", joinColumns = {@JoinColumn(name = "reservation_id")}, inverseJoinColumns = {@JoinColumn(name = "room_id")})
    private List<Room> rooms;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column
    private int numberOfAdults;

    @Column
    private int numberOfChildren;

    public Reservation() {
        customers = new LinkedList<>();
        rooms = new LinkedList<>();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public boolean addCustomer(Customer c) {
        return customers.add(c);
    }

    public boolean removeCustomer(Customer c) {
        return customers.remove(c);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public boolean addRoom(Room r) {
        return rooms.add(r);
    }

    public boolean removeRoom(Room r) {
        return rooms.remove(r);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getNumberOfAdults() { return numberOfAdults; }

    public void setNumberOfAdults(int numberOfAdults) { this.numberOfAdults = numberOfAdults; }

    public int getNumberOfChildren() { return numberOfChildren; }

    public void setNumberOfChildren(int numberOfChildren) { this.numberOfChildren = numberOfChildren; }

    public Long getNights() {
        LocalDate startDate = Instant.ofEpochMilli(getStartDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = Instant.ofEpochMilli(getEndDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
