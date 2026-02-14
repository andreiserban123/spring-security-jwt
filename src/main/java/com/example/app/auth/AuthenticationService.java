package com.example.app.auth;

import com.example.app.auth.request.AuthenticationRequest;
import com.example.app.auth.request.RefreshRequest;
import com.example.app.auth.request.RegistrationRequest;
import com.example.app.auth.response.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse login(AuthenticationRequest request);

    void register(RegistrationRequest request);

    AuthenticationResponse refresh(RefreshRequest request);
}
