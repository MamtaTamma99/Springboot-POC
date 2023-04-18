package com.springbootPOC.controller;

import com.springbootPOC.dto.AuthResponse;
import com.springbootPOC.dto.LoginRequestDTO;
import com.springbootPOC.dto.SignupRequestDTO;
import com.springbootPOC.service.AuthService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mamta.t
 */
@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequestDTO loginRequest) {
        log.debug("Login user by emailId request received.");
        AuthResponse authResponse = authService.login(loginRequest);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody @Valid SignupRequestDTO signupRequestDTO) {
        log.debug("Login user by emailId request received.");
        AuthResponse authResponse = authService.signup(signupRequestDTO);
        return ResponseEntity.ok(authResponse);
    }
}
