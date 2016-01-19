package sese2015.g3.goldenlion.customer.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import sese2015.g3.goldenlion.commons.rest.StringToLocalDateDeserializer;
import sese2015.g3.goldenlion.customer.domain.Address;
import sese2015.g3.goldenlion.customer.domain.Customer;
import sese2015.g3.goldenlion.customer.domain.Gender;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Mario on 22.11.2015.
 */
public class CustomerDto {
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    @JsonDeserialize(using = StringToLocalDateDeserializer.class)
    private LocalDate birthday;
    @NotNull
    private Gender gender;
    @NotBlank
    private String street;
    @NotBlank
    private String streetExtension;
    @NotBlank
    private String state;
    @NotBlank
    private String postalCode;
    @NotBlank
    private String city;
    private String companyName;
    @NotNull
    private Double discount;
    @NotBlank
    private String telephone;
    @NotBlank
    private String fax;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String web;
    @NotBlank
    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetExtension() {
        return streetExtension;
    }

    public void setStreetExtension(String streetExtension) {
        this.streetExtension = streetExtension;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Customer toCustomer() {
        Address address = new Address();
        address.setCity(this.getCity());
        address.setCountry(this.getState());
        address.setStreet(this.getStreet());
        address.setStreetNumber(this.getStreetExtension());
        address.setZipCode(String.valueOf(this.getPostalCode()));

        Customer customer = new Customer();
        customer.setBillingAddress(address);
        customer.setFirstName(this.getFirstName());
        customer.setLastName(this.getLastName());
        customer.setBirthday(Date.from(this.getBirthday().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        customer.setCompanyName(this.getCompanyName());
        customer.setDiscount(this.getDiscount());
        customer.setEmail(this.getEmail());
        customer.setFaxNumber(this.getFax());
        customer.setGender(this.getGender());
        customer.setNote(this.getNotes());
        customer.setPhoneNumber(this.getTelephone());
        customer.setWebsite(this.getWeb());

        return customer;
    }
}
