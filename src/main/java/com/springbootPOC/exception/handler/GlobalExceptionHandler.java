package com.springbootPOC.exception.handler;

import com.springbootPOC.dto.DefaultResponse;
import com.springbootPOC.exception.DefaultException;
import com.springbootPOC.exception.InvalidArgumentException;
import com.springbootPOC.exception.ResourceNotFoundException;
import com.springbootPOC.exception.WebApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * @author mamta.t
 *      A convenient base class for {@link RestControllerAdvice @ControllerAdvice} classes that wish to provide centralized
 *      exception handling across all {@code @RequestMapping} methods through {@code @ExceptionHandler} methods.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<DefaultResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
                log.error("Request failed. request : {} {}.", request, ex);
                DefaultResponse defaultResponse = new DefaultResponse(HttpStatus.NOT_FOUND, ex.getMessage());
                return new ResponseEntity(defaultResponse,HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(WebApplicationException.class)
        public final ResponseEntity<DefaultResponse> handleRestException(WebApplicationException ex, WebRequest request) {
                log.info("Request failed. reason : {} , request : {}, data : {}", ex.getMessage(), request, ex.getData());
                DefaultResponse defaultResponse = new DefaultResponse(ex.getMessageCode(), ex.getData());
                return new ResponseEntity<>(defaultResponse, ex.getHttpStatus());
        }

        @ExceptionHandler(DefaultException.class)
        public final ResponseEntity<DefaultResponse> handleDefaultException(DefaultException ex, WebRequest request) {
                log.error("Request failed. request : {} {}.", request, ex);
                DefaultResponse defaultResponse = new DefaultResponse(HttpStatus.INTERNAL_SERVER_ERROR.value());
                return new ResponseEntity<>(defaultResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(InvalidArgumentException.class)
        public final ResponseEntity<DefaultResponse> handleInvalidArgumentException(InvalidArgumentException ex, WebRequest request) {
                log.error("Request failed. request : {} {}.", request, ex);
                DefaultResponse defaultResponse = new DefaultResponse(ex.getMessageCode(), ex.getMessage());
                return new ResponseEntity<>(defaultResponse, ex.getHttpStatus());
        }
}
