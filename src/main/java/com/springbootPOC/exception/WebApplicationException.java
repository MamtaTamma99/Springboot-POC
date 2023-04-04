package com.springbootPOC.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * @author mamta.t
 */
@Data
public class WebApplicationException extends DefaultException{

    private final HttpStatus httpStatus;
    private final Integer messageCode;
    private transient Map<String, Object> data;
    private String reason;


    public WebApplicationException(HttpStatus httpStatus, Integer messageCode, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.messageCode = messageCode;
    }
}
