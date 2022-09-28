package com.permisitelu.api.exception;

import com.permisitelu.api.common.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class HandleException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> serverErrorHandleException(RuntimeException ex) {
        ExceptionMessage message = new ExceptionMessage(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundHandleException(NotFoundException ex) {
        ExceptionMessage message = new ExceptionMessage(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FoundException.class)
    public ResponseEntity<?> foundHandleException(FoundException ex) {
        ExceptionMessage message = new ExceptionMessage(ex.getMessage(), HttpStatus.FOUND);
        return new ResponseEntity<>(message, HttpStatus.FOUND);
    }
}
