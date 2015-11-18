package sese2015.g3.goldenlion.customer.domain;

import sese2015.g3.goldenlion.commons.entity.PersistentObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "addresses")
public class Address extends PersistentObject {

    @Column
    @NotNull
    private String street;

    @Column(length = 50)
    @NotNull
    private String streetNumber;

    @Column(length = 16)
    @NotNull
    private String zipCode;

    @Column
    @NotNull
    private String city;

    @Column
    @NotNull
    private String country;
}
