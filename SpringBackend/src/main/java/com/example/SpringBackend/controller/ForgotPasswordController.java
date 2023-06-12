package com.example.SpringBackend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBackend.model.User;
import com.example.SpringBackend.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class ForgotPasswordController {
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/checkemail")
    public ResponseEntity<String> checkEmail(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        if (emailExists(email)) {
            return ResponseEntity.ok().body("Email verified");
        } else {
            return new ResponseEntity<>("Email not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/updatepassword")
    public ResponseEntity<String> updatePassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String newPassword = request.get("newPassword");
        if (updatePasswordByEmail(email, newPassword)) {
            return ResponseEntity.ok().body("Password updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update password");
        }
    }

    private boolean emailExists(String email) {
        // Use your UserRepository to check if the email exists in the database
        return userRepository.existsByEmail(email);
    }

    private boolean updatePasswordByEmail(String email, String newPassword) {
        // Use your UserRepository to update the password for the specific email
        User user = userRepository.findByEmail(email);

        if (user != null) {
            user.setPassword(newPassword);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
}
