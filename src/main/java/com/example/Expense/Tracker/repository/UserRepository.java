package com.example.Expense.Tracker.repository;

/*
 * PURPOSE OF THIS FILE:
 * ----------------------
 * This repository interface provides database operations for the User entity.
 * It extends JpaRepository to inherit standard CRUD operations and also
 * defines custom query methods for checking existence and retrieving users
 * using unique fields such as email and username.
 *
 * In short: This interface handles all User-related database queries.
 */

import com.example.Expense.Tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Marks this as a Spring Data repository for dependency injection
public interface UserRepository extends JpaRepository<User, Long> {

    // Checks if a user already exists with the given email
    boolean existsByEmail(String email);

    // Checks if a user already exists with the given username
    boolean existsByUsername(String username);

    // Fetches a user based on their username (used during login)
    User findByUsername(String username);
}
