package sese2015.g3.goldenlion.reservation.domain;

import sese2015.g3.goldenlion.commons.entity.PersistentObject;
import sese2015.g3.goldenlion.customer.domain.Customer;
import sese2015.g3.goldenlion.room.domain.Room;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reservations")
public class Reservation extends PersistentObject {

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "reservation_customers", joinColumns = {@JoinColumn(name = "reservation_id")}, inverseJoinColumns = {@JoinColumn(name = "customer_id")})
    private List<Customer> customers;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "reservation_rooms", joinColumns = {@JoinColumn(name = "reservation_id")}, inverseJoinColumns = {@JoinColumn(name = "room_id")})
    private List<Room> rooms;

    @Column
    private double discount;

    @Column
    private double price;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date endDate;
}
