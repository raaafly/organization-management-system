package com.permisitelu.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class FoundException extends RuntimeException{
    public FoundException() {
        super();
    }

    public FoundException(String message) {
        super(message);
    }

    public FoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
