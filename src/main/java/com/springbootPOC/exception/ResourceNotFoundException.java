package com.springbootPOC.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mamta.t
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException{

    String resourceName;
    String fieldName;
    long fieldValue;

}
