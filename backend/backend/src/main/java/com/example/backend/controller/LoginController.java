//package com.example.backend.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.backend.model.User;
//import com.example.backend.repository.UserRepository;
//
////@RestController
////public class LoginController {
////	
////	@CrossOrigin(origins = "http://localhost:3000")
////    @PostMapping("/login")
////    public ResponseEntity<String> login(@RequestBody Login login) {
////        // Hard-coded authentication logic
////        if (login.getUsername().equals("admin") && login.getPassword().equals("Password@1")) {
////            return ResponseEntity.ok().build();
////        } else {
////            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
////        }
////    }
////}
//
//
//@RestController
//@CrossOrigin(origins = "http://localhost:3000")
//public class LoginController {
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public LoginController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @PostMapping("/api/login")
//    public ResponseEntity<String> login(
//            @RequestParam("username") String username,
//            @RequestParam("password") String password) {
//
//        // Check if the provided username and password match an existing user in the database
//        User user = userRepository.findByUsername(username);
//        if (user != null && user.getPassword().equals(password)) {
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//        }
//    }
//}
//
package com.example.backend.controller;

//import com.example.backend.model.User;
//import com.example.backend.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:3000")
//public class LoginController {
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public LoginController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @PostMapping("/api/login")
//    public ResponseEntity<String> login(
//            @RequestParam("username") String username,
//            @RequestParam("password") String password) {
//    	
//    	System.out.println("Username: " + username);
//        System.out.println("Password: " + password);
//
//        // Check if the provided username exists in the database
//        User user = userRepository.findByUsername(username);
//        if (user != null) {
//            // Check if the provided password matches the stored password
//            if (user.getPassword().equals(password)) {
//                return ResponseEntity.ok("Login successful");
//            } else {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username");
//        }
//    }
//}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;

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



