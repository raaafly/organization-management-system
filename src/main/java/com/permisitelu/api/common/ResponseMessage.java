package com.permisitelu.api.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@JsonPropertyOrder({"timestamp", "status", "message", "detail", "payload"})
@JsonIgnoreProperties({"code"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage {
    private Date timestamp;
    private HttpStatus code;
    private String detail;
    private Object payload;

    public ResponseMessage(String detail, HttpStatus code) {
        this.timestamp = new Date();
        this.detail = detail;
        this.code = code;
    }

    public ResponseMessage(String detail, Object payload, HttpStatus code) {
        this.timestamp = new Date();
        this.detail = detail;
        this.payload = payload;
        this.code = code;
    }

    public int getStatus() {
        return this.code.value();
    }

    public String getMessage() {
        return this.code.getReasonPhrase();
    }
}
