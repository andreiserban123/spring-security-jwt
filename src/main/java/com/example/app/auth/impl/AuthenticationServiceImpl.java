package com.example.app.auth.impl;

import com.example.app.auth.AuthenticationService;
import com.example.app.auth.request.AuthenticationRequest;
import com.example.app.auth.request.RefreshRequest;
import com.example.app.auth.request.RegistrationRequest;
import com.example.app.auth.response.AuthenticationResponse;
import com.example.app.exception.BusinessException;
import com.example.app.exception.ErrorCode;
import com.example.app.role.RoleRepository;
import com.example.app.security.JwtService;
import com.example.app.user.User;
import com.example.app.user.UserMapper;
import com.example.app.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;


    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {

        final Authentication auth = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        final User user = (User) auth.getPrincipal();
        final String token = this.jwtService.generateAccessToken(user.getUsername());
        final String refreshToken = this.jwtService.generateRefreshToken(user.getUsername());
        final String tokenType = "Bearer";
        return AuthenticationResponse
                .builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .tokenType(tokenType)
                .build();
    }

    @Override
    @Transactional
    public void register(RegistrationRequest request) {
        checkUserEmail(request.getEmail());
        checkUserPhoneNumber(request.getPhoneNumber());
        checkPasswords(request.getPassword(), request.getConfirmPassword());

    }

    private void checkUserEmail(String email) {
        final boolean emailExists = this.userRepository.existsByEmailIgnoreCase(email);
        if (emailExists) {
            throw new BusinessException(ErrorCode.EMAIL_ALREADY_EXISTS, email);
        }
    }

    private void checkUserPhoneNumber(String phoneNumber) {
    }

    private void checkPasswords(String password, String confirmPassword) {
    }

    @Override
    public AuthenticationResponse refresh(RefreshRequest request) {
        return null;
    }
}
