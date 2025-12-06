package com.example.Expense.Tracker.service;

/*
 * PURPOSE OF THIS FILE:
 * ----------------------
 * This service handles user-related business logic such as registration
 * and login. It validates credentials, checks for duplicate users, and
 * interacts with the UserRepository to persist or retrieve user data.
 *
 * In short: This class manages user authentication and account creation.
 */

import com.example.Expense.Tracker.model.User;
import com.example.Expense.Tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Marks this as a Spring-managed service component
public class UserService {

    @Autowired
    UserRepository userRepo; // Repository for performing user DB operations

    // Registers a new user after validating uniqueness
    public User registerUser(User user) {

        // Check if username already exists
        if (userRepo.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        // Check if email address is already in use
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Save the new user in the database
        return userRepo.save(user);
    }

    // Authenticates a user using username and password
    public User login(String username, String password) {
        User user = userRepo.findByUsername(username);

        // Validate that user exists and password matches
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        throw new RuntimeException("Invalid username or password");
    }
}
