package com.example.backend.controller;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class ForgotPasswordController {

    private final UserRepository userRepository;

    @Autowired
    public ForgotPasswordController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/forgotpassword")
    public ResponseEntity<String> forgotPassword(
            @RequestParam("email") String email,
            @RequestParam("newPassword") String newPassword) {

        // Check if the email exists in the database
        User user = userRepository.findByEmail(email);
        if (user != null) {
            // Update the user's password with the new password
            user.setPassword(newPassword);

            // Save the updated user in the database
            userRepository.save(user);

            return ResponseEntity.ok("Password updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found");
        }
    }
}

