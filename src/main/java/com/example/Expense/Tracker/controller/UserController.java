package com.example.Expense.Tracker.controller;

/*
 * PURPOSE OF THIS FILE:
 * ----------------------
 * This REST controller handles all user-related HTTP requests such as
 * registration and login. It interacts with the UserService to validate
 * user input, manage authentication, and return appropriate responses.
 *
 * In short: This class controls user signup and login API endpoints.
 */

import com.example.Expense.Tracker.model.User;
import com.example.Expense.Tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Exposes this class as a REST API returning JSON
@RequestMapping("/ExpTrack") // Base path for all user operations
@CrossOrigin // Allows frontend (e.g., React/Vue/HTML) to call these APIs
public class UserController {

    @Autowired
    UserService userService; // Handles registration and login logic

    // -------------------- API: User Registration --------------------

    /*
     * Registers a new user in the system.
     * If username or email already exists, a BAD_REQUEST response is returned.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User savedUser = userService.registerUser(user);
            return ResponseEntity.ok(savedUser); // Registration successful
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage()); // Return validation error message
        }
    }

    // -------------------- API: User Login --------------------

    /*
     * Authenticates a user using username and password.
     * Returns the user object on success, otherwise returns UNAUTHORIZED.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            User loggedInUser = userService.login(user.getUsername(), user.getPassword());
            return ResponseEntity.ok(loggedInUser); // Login successful
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage()); // Invalid credentials
        }
    }

}
