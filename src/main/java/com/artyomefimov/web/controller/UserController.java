package com.artyomefimov.web.controller;

import com.artyomefimov.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @Autowired
    public UserController(AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping(value = "/api/login")
    public ResponseEntity<?> login(HttpServletRequest request, @RequestBody String typeAndCredentials) {
        String onlyCredentials = typeAndCredentials.substring(typeAndCredentials.lastIndexOf("basic ") + 6, typeAndCredentials.length() - 3);
        String decodedCredentials = new String(Base64.getDecoder().decode(
                onlyCredentials.getBytes(StandardCharsets.UTF_8)));
        String[] split = decodedCredentials.split(":");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(split[0], split[1]));

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);
        request.getSession().setAttribute(SPRING_SECURITY_CONTEXT_KEY, context);

        return ResponseEntity.ok(tokenProvider.generateToken(authentication));
    }
}
