package com.permisitelu.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotAcceptException extends RuntimeException {
    public NotAcceptException() {
        super();
    }

    public NotAcceptException(String message) {
        super(message);
    }

    public NotAcceptException(String message, Throwable cause) {
        super(message, cause);
    }
}
