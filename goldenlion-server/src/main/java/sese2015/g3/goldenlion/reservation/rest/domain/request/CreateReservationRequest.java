package sese2015.g3.goldenlion.reservation.rest.domain.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateReservationRequest {

    private long roomId;

    private Date startDate;

    private Date endDate;

    private List<Long> customerIds;

    public CreateReservationRequest() {
        customerIds = new ArrayList<>();
    }

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

    public List<Long> getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(List<Long> customerIds) {
        this.customerIds = customerIds;
    }
}
