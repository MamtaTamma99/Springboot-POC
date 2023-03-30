package com.restCrud.exception.handler;

import com.restCrud.dto.ApiResponse;
import com.restCrud.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.restCrud.constants.ResponseMessageCodes.NOT_FOUND;

/**
 * @author mamta.t
 *      A convenient base class for {@link RestControllerAdvice @ControllerAdvice} classes that wish to provide centralized
 *      exception handling across all {@code @RequestMapping} methods through {@code @ExceptionHandler} methods.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ApiResponse> handleResourceotFoundException(ResourceNotFoundException ex){
                ApiResponse apiResponse = new ApiResponse(HttpStatus.NOT_FOUND, ex.getMessage());
                return new ResponseEntity(apiResponse,HttpStatus.NOT_FOUND);
        }
}
