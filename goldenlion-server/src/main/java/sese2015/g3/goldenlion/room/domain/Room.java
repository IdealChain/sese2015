package sese2015.g3.goldenlion.room.domain;

import sese2015.g3.goldenlion.commons.entity.PersistentObject;

import javax.persistence.Column;
import javax.persistence.Entity;
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
}
