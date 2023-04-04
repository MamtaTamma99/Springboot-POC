package com.springbootPOC.exception;

import lombok.Data;

/**
 * @author mamta.t
 */
public class DefaultException extends Exception{
    public DefaultException() {
    }

    public DefaultException(String message) {
        super(message);
    }

}
