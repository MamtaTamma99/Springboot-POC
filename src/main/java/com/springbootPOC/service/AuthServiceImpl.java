package com.springbootPOC.service;

import com.springbootPOC.dbo.User;
import com.springbootPOC.dto.AuthResponse;
import com.springbootPOC.dto.LoginRequestDTO;
import com.springbootPOC.dto.SignupRequestDTO;
import com.springbootPOC.mapper.UserMapper;
import com.springbootPOC.repository.UserRepository;
import com.springbootPOC.utils.JWTUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author mamta.t
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService{

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder pwdEncoder;

    @Override
    public AuthResponse login(LoginRequestDTO loginRequest) {
        log.info("Inside login method");
        authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
        log.info("Successfully fetched User with emailId" + loginRequest.getEmail());
        String jwtToken = jwtUtil.generateJwtToken(user);
        return new AuthResponse(jwtToken);

    }

    @Override
    public AuthResponse signup(@Valid @RequestBody SignupRequestDTO signupRequest) {
        log.info("Inside signUp method");
        User user = User.builder().username(signupRequest.getUsername()).firstName(signupRequest.getFirstName()).lastName(signupRequest.getLastName()).email(signupRequest.getEmail()).
                password(pwdEncoder.encode(signupRequest.getPassword())).build();
        userRepository.save(user);
        String jwtToken = jwtUtil.generateJwtToken(user);
        return new AuthResponse(jwtToken);
    }
}
