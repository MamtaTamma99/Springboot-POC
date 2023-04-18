package com.springbootPOC.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author mamta.t
 */
@Component
@Data
public class JwtConfig {

    @Value("${jwt.token.secretkey}")
    private String secretKey;
}
