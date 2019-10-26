package com.artyomefimov.web.controller;

import com.artyomefimov.database.model.User;
import com.artyomefimov.database.repository.UserRepository;
import com.artyomefimov.web.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.encoder = new BCryptPasswordEncoder();
    }

    @GetMapping("/login")
    public ResponseEntity<User> login(@RequestBody String loginInfo) {
        String[] loginAndPassword = loginInfo.split("___");
        String login = loginAndPassword[0];
        String password = loginAndPassword[1];
        Optional<User> userOptional = userRepository.findByLogin(login);
        return userOptional.map(u -> {
            String encryptedPassword = encoder.encode(password);
            if (encryptedPassword.equals(u.getPassword()))
                return new ResponseEntity<>(u, HttpStatus.OK);
            else
                return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
