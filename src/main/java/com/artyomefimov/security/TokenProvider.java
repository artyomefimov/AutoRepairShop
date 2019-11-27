package com.artyomefimov.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class TokenProvider {
    private String token;
    private Authentication authentication;

    public String getToken() {
        return token;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public String generateToken(Authentication authentication) {
        if (token == null) {
            SecurityUser userPrincipal = (SecurityUser) authentication.getPrincipal();
            token = new String(
                    Base64.getEncoder().encode(
                            (userPrincipal.getUsername() + userPrincipal.getPassword()).getBytes()
                    ));
            this.authentication = authentication;
        }
        return token;
    }
}
