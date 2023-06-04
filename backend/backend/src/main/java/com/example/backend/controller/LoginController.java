package com.example.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.Login;

//@RestController
//public class LoginController {
//	
//	@CrossOrigin(origins = "http://localhost:3000")
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody Login login) {
//        // Hard-coded authentication logic
//        if (login.getUsername().equals("admin") && login.getPassword().equals("Password@1")) {
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }
//}

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/login")
public class LoginController {

    @PostMapping
    public ResponseEntity<String> login(@RequestBody Login login) {
        String username = login.getUsername();
        String password = login.getPassword();

        if (isValidUsernameAndPassword(username, password)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    private boolean isValidUsernameAndPassword(String username, String password) {
        return "admin".equals(username) && "Password@1".equals(password);
    }

}