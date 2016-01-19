package sese2015.g3.goldenlion.commons.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.GONE)
public class ResourceInvalidatedException extends RuntimeException {

    public ResourceInvalidatedException(String message) {
        super(message);
    }
}
