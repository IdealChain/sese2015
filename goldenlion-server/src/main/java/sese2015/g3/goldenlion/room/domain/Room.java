package sese2015.g3.goldenlion.room.domain;

import sese2015.g3.goldenlion.commons.entity.PersistentObject;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "rooms")
public class Room extends PersistentObject {

    @NotNull
    private String roomNumber;

    @NotNull
    private int maxPersons;

    private Double price1P;

    private Double price2P;

    private Double price3P;

    private Double price1P1K;

    private Double price1P2K;

    private Double price2P1K;

    @NotNull
    private double pricePerNight;

    @Lob
    private String roomImage;

    @Lob
    private String description;

    public Room() {
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(int maxPersons) {
        this.maxPersons = maxPersons;
    }

    public Double getPrice1P() {
        return price1P;
    }

    public void setPrice1P(Double price1P) {
        this.price1P = price1P;
    }

    public Double getPrice2P() {
        return price2P;
    }

    public void setPrice2P(Double price2P) {
        this.price2P = price2P;
    }

    public Double getPrice3P() {
        return price3P;
    }

    public void setPrice3P(Double price3P) {
        this.price3P = price3P;
    }

    public Double getPrice1P1K() {
        return price1P1K;
    }

    public void setPrice1P1K(Double price1P1K) {
        this.price1P1K = price1P1K;
    }

    public Double getPrice1P2K() {
        return price1P2K;
    }

    public void setPrice1P2K(Double price1P2K) {
        this.price1P2K = price1P2K;
    }

    public Double getPrice2P1K() {
        return price2P1K;
    }

    public void setPrice2P1K(Double price2P1K) {
        this.price2P1K = price2P1K;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(String roomImage) {
        this.roomImage = roomImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRate(int numberOfAdults, int numberOfChildren)
    {
        if(numberOfAdults == 1 && numberOfChildren == 0) {
            return this.getPrice1P();
        }
        else if(numberOfAdults == 1 && numberOfChildren == 1) {
            return this.getPrice1P1K();
        }
        else if(numberOfAdults == 1 && numberOfChildren == 2) {
            return this.getPrice1P2K();
        }
        else if(numberOfAdults == 2 && numberOfChildren == 0) {
            return this.getPrice2P();
        }
        else if(numberOfAdults == 2 && numberOfChildren == 1) {
            return this.getPrice2P1K();
        }
        else if(numberOfAdults == 3 && numberOfChildren == 0) {
            return this.getPrice3P();
        }
        else{
            // simply return the max rate
            return this.getPrice3P();
        }
    }
}
