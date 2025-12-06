package com.example.Expense.Tracker.model;

/*
 * PURPOSE OF THIS FILE:
 * ----------------------
 * This entity represents a user in the Expense Tracker system.
 * It stores the user's core account details such as username, email,
 * and password, and maintains a one-to-many relationship with all
 * transactions made by the user.
 *
 * In short: This class models the system's users and their recorded transactions.
 */

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data // Lombok annotation to auto-generate getters, setters, equals, hashCode, toString
@Entity // Marks this class as a JPA entity for database persistence
@Table(name = "users") // Maps to the "users" table in the database
public class User {

    @Id // Primary key for the user
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrementing ID
    private Long id;

    @Column(unique = true) // No two users can have the same username
    private String username;

    @Column(unique = true) // Ensures email uniqueness across all users
    private String email;

    private String password; // Hashed password for authentication

    @OneToMany(
            mappedBy = "user",                 // "user" field in Transaction owns the relationship
            cascade = CascadeType.ALL,         // Any change to User cascades to its transactions
            orphanRemoval = true               // Deletes transactions if removed from the list
    )
    @JsonManagedReference // Manages the serialization side of bidirectional relationship
    private List<Transaction> transactions = new ArrayList<>(); // List of all user's transactions
}
