package com.springbootPOC.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author mamta.t
 *      DefaultResponse class common response for all Exception
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultResponse<T> {

    private Integer code;

    private T data;

    @JsonIgnore
    private HttpStatus httpStatus;

    public DefaultResponse(Integer code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public DefaultResponse(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public DefaultResponse(HttpStatus httpStatus, String message) {
    }

    public DefaultResponse(int value) {
    }

    public ResponseEntity<DefaultResponse<T>> build() {
        return new ResponseEntity<>(this, this.httpStatus);
    }
}
