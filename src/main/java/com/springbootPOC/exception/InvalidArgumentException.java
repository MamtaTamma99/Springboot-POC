package com.springbootPOC.exception;


import lombok.ToString;
import org.springframework.http.HttpStatus;

import static com.springbootPOC.constants.ResponseMessageCodes.BAD_REQUEST;

/**
 * @author mamta.t
 */
@ToString
public class InvalidArgumentException extends WebApplicationException{

    public InvalidArgumentException(String message) {
        super(HttpStatus.BAD_REQUEST, BAD_REQUEST.getCode(), message);
    }
}
