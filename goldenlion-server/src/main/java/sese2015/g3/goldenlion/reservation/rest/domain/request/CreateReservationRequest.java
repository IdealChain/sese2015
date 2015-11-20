package sese2015.g3.goldenlion.reservation.rest.domain.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateReservationRequest {

    private int roomId;

    private Date startDate;

    private Date endDate;

    private List<Integer> customerIds;

    public CreateReservationRequest() {
        customerIds = new ArrayList<>();
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
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

    public List<Integer> getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(List<Integer> customerIds) {
        this.customerIds = customerIds;
    }
}
