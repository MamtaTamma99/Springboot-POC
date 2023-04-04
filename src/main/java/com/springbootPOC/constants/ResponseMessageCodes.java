package com.springbootPOC.constants;

/**
 * @author mamta.t
 */
public enum ResponseMessageCodes {
    SUCCESS(2000),
    NO_CONTENT(2004),

    BAD_REQUEST(4000);

    public int getCode() {
        return code;
    }

    private final int code;
    ResponseMessageCodes(int code) {
        this.code = code;
    }
}
