package com.example.app.security;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

@Service
public class JwtService {
    private static final String TOKEN_TYPE = "token_type";

    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    public JwtService() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        this.privateKey = KeyUtils.loadPrivateKey("keys/local-only/private_key.pem");
        this.publicKey = KeyUtils.loadPublicKey("keys/local-only/public_key.pem");
    }
}
