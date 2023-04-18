package com.springbootPOC.service;

import com.springbootPOC.dto.AuthResponse;
import com.springbootPOC.dto.LoginRequestDTO;
import com.springbootPOC.dto.SignupRequestDTO;

/**
 * @author mamta.t
 */
public interface AuthService {
    AuthResponse login(LoginRequestDTO loginRequest);

    AuthResponse signup(SignupRequestDTO signupRequest);
}
