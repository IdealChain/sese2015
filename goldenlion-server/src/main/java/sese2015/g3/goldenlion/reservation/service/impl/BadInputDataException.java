package sese2015.g3.goldenlion.reservation.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadInputDataException extends RuntimeException {

    public BadInputDataException() {
        super();
    }

    public BadInputDataException(String message) {
        super(message);
    }
}
