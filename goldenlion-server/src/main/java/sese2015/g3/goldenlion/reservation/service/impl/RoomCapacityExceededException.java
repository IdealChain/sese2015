package sese2015.g3.goldenlion.reservation.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class RoomCapacityExceededException extends RuntimeException {

    public RoomCapacityExceededException() {
        super();
    }

    public RoomCapacityExceededException(String message) {
        super(message);
    }
}
