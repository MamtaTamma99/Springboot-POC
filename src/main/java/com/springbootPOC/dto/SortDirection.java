package com.springbootPOC.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author mamta.t
 */

public enum SortDirection {

    ASC("ASC"),
    DESC("DESC");

    private final String direction;

    private static final Map<String, SortDirection> valueMap;

    static {
        valueMap = Arrays.stream(SortDirection.values()).collect(Collectors.toMap(SortDirection::getDirection, direction -> direction));
    }
    SortDirection(String direction) {
        this.direction = direction;
    }
    public String getDirection() {
        return direction;
    }

}
