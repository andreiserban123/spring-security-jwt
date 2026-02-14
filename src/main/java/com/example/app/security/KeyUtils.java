package com.example.app.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class KeyUtils {
    private KeyUtils() {
    }


    public static PrivateKey loadPrivateKey(final String pemPath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        final String key = readKeyFromResource(pemPath).replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");

        final byte[] decoded = java.util.Base64.getDecoder().decode(key);
        final PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }

    public static PublicKey loadPublicKey(final String pemPath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        final String key = readKeyFromResource(pemPath)
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");

        final byte[] decoded = java.util.Base64.getDecoder().decode(key);
        final X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        return KeyFactory.getInstance("RSA").generatePublic(spec);
    }

    private static String readKeyFromResource(String pemPath) throws IOException {
        try (final InputStream inputStream = KeyUtils.class.getResourceAsStream(pemPath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Resource not found: " + pemPath);
            }
            return new String(inputStream.readAllBytes());
        }
    }
}
