package sese2015.g3.goldenlion.reservation.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictingDataException extends RuntimeException {

    public ConflictingDataException() {
        super();
    }

    public ConflictingDataException(String message) {
        super(message);
    }
}
