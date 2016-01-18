package sese2015.g3.goldenlion.hotel.domain;

import org.hibernate.validator.constraints.NotBlank;
import sese2015.g3.goldenlion.commons.entity.PersistentObject;
import sese2015.g3.goldenlion.customer.domain.Address;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "hotel")
public class Hotel extends PersistentObject {

    @NotBlank
    @Column
    private String name;

    @Column
    private String logo;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
