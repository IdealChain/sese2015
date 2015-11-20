package sese2015.g3.goldenlion.room.domain;

import sese2015.g3.goldenlion.commons.entity.PersistentObject;

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

    //TODO implement prices per 1, 2, 3 persons + 1, 2 kids
}