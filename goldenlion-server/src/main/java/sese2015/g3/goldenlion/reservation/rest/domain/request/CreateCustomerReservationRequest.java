package sese2015.g3.goldenlion.reservation.rest.domain.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Mario on 06.12.2015.
 */
public class CreateCustomerReservationRequest {

    private long roomId;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @Min(value = 1, message = "Für die Reservierung muss eine Mindestanzahl von 1 Person angegeben werden!")
    private int numberOfAdults;

    private int numberOfChildren;

    @NotNull
    private ReservationCustomer customer;

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
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

    public ReservationCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(ReservationCustomer customer) {
        this.customer = customer;
    }

    public int getNumberOfChildren() { return numberOfChildren; }

    public void setNumberOfChildren(int numberOfChildren) { this.numberOfChildren = numberOfChildren; }

    public int getNumberOfAdults() { return numberOfAdults; }

    public void setNumberOfAdults(int numberOfAdults) { this.numberOfAdults = numberOfAdults; }
}
