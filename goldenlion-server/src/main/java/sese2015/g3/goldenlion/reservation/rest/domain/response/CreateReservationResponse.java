package sese2015.g3.goldenlion.reservation.rest.domain.response;

public class CreateReservationResponse {
    private long reservationId;

    public CreateReservationResponse(long reservationId) {
        this.reservationId = reservationId;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }
}
