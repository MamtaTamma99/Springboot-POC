package com.restCrud.constants;

/**
 * @author mamta.t
 */
public enum ResponseMessageCodes {


    NOT_FOUND(404);

    private final int code;
    ResponseMessageCodes(int code) {
        this.code = code;
    }
}
