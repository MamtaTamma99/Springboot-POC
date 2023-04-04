package com.springbootPOC.constants;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author mamta.t
 */
public enum UserSortingColumns {

    USER_NAME("username"),
    FIRST_NAME("firstName"),
    LAST_NAME("lastName"),
    EMAIL("email");

    private final String sortColumn;
    private static final Map<String, UserSortingColumns> valueMap;


    static {
        valueMap = Arrays.stream(UserSortingColumns.values()).collect(Collectors.toMap(UserSortingColumns::getSortColumn, sortColumn -> sortColumn));
    }

    UserSortingColumns(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    @JsonCreator
    public static UserSortingColumns fromValue(String value) {
        return valueMap.get(value);
    }
}
