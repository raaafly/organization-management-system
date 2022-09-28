package com.permisitelu.api.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Date;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage {
    private Date timestamp;
    private String message;
    private Object payload;

    public ResponseMessage(String message) {
        this.timestamp = new Date();
        this.message = message;
    }

    public ResponseMessage(String message, Object payload) {
        this.timestamp = new Date();
        this.message = message;
        this.payload = payload;
    }
}
