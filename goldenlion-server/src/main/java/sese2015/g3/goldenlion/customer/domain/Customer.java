package sese2015.g3.goldenlion.customer.domain;

import org.hibernate.validator.constraints.Email;
import sese2015.g3.goldenlion.commons.entity.PersistentObject;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "customers")
public class Customer extends PersistentObject {

    @NotNull
    @Column
    private String firstName;

    @NotNull
    @Column
    private String lastName;

    @Column
    private String companyName;

    @NotNull
    @Email
    @Column(length = 50, unique = true)
    private String email;

    @NotNull
    @Column(length = 6)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "billingaddress_id")
    private Address billingAddress;

    @Column(length = 65535)
    private String note;

    @Column
    private double discount;

    @Column
    private String phoneNumber;

    @Column
    private String faxNumber;

    @Column
    private String website;
}
