package sese2015.g3.goldenlion.reservation.rest.domain.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import sese2015.g3.goldenlion.commons.rest.StringToLocalDateDeserializer;
import sese2015.g3.goldenlion.customer.domain.Gender;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by Mario on 06.12.2015.
 */
public class ReservationCustomer
{
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
    @NotBlank
    private String telephone;
    @NotBlank
    private String fax;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String web;

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
}
