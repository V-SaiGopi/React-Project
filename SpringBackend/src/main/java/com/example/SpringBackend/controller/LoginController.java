package com.example.SpringBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.SpringBackend.model.User;
import com.example.SpringBackend.repository.UserRepository;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    private final UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody UserCredentials credentials) {
        // Retrieve the user from the database based on the provided username
        User user = userRepository.findByUsername(credentials.getUsername());

        if (user != null && user.getPassword().equals(credentials.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    public static class UserCredentials {
        private String username;
        private String password;

        // Constructors, getters, and setters

        public UserCredentials() {
        }

        public UserCredentials(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}