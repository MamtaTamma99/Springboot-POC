package com.springbootPOC.utils;

import com.springbootPOC.dbo.User;
import com.springbootPOC.security.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author mamta.t
 */
@Component
@Slf4j
public class JWTUtil {
    @Autowired
    private JwtConfig jwtConfig;

    public String generateJwtToken(User user) {

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecretKey())
                .compact();
    }

    public boolean validateJwtToken(String jwtToken) {
        try {
            Jwts.parser().setSigningKey(jwtConfig.getSecretKey()).parseClaimsJws(jwtToken);
            return true;
        } catch (ExpiredJwtException ex) {
            log.error("JWT expired", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            log.error("JWT is not supported", ex);
        } catch (SignatureException ex) {
            log.error("Signature validation failed");
        }
        return false;
    }

    public String getSubject(String jwtToken) {
        return parseClaims(jwtToken).getSubject();
    }

    private Claims parseClaims(String jwtToken) {
        return Jwts.parser().setSigningKey(jwtConfig.getSecretKey()).parseClaimsJws(jwtToken).getBody();
    }
}
