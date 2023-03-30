package com.restCrud.constants;

/**
 * @author mamta.t
 */
public class RegExConstants {
    private RegExConstants() {

    }

    public static final String ALPHANUMERIC_WITH_DOT_AND_SPACE_REGEX = "^$|^[a-zA-Z0-9. ]*$";

    public static final String EMAIL_REGEX = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+])[A-Za-z\\d!@#$%^&*()_+]{6,20}";


}
