package com.restCrud.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author mamta.t
 *      DefaultResponse class common response for all Exception
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private Integer code;

    private T data;

    @JsonIgnore
    private HttpStatus httpStatus;

    public ApiResponse(Integer code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public ApiResponse(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ApiResponse(HttpStatus httpStatus, String message) {
    }
}
