package com.permisitelu.api.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@JsonIgnoreProperties({ "code" })
@JsonPropertyOrder({ "timestamp", "status", "message", "detail" })
public class ExceptionMessage {
    private final Date timestamp;
    private final HttpStatus code;
    private final String detail;

    public ExceptionMessage(String detail, HttpStatus code) {
        this.timestamp = new Date();
        this.detail = detail;
        this.code = code;
    }

    public int getStatus() {
        return this.code.value();
    }

    public String getMessage() {
        return this.code.getReasonPhrase();
    }
}
