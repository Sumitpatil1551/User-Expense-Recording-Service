package com.example.Expense.Tracker.model;

/*
 * PURPOSE OF THIS FILE:
 * ----------------------
 * This entity represents a financial transaction recorded by a user
 * in the Expense Tracker system.
 *
 * It stores the transaction description, amount, date, and maintains
 * a link to the User who created the transaction.
 *
 * In short: This class models each expense/income entry made by a user.
 */

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data // Lombok annotation that generates getters, setters, toString, equals, hashCode
@Entity // Marks this class as a JPA entity mapped to a database table
@Table(name = "transaction") // Maps this entity to the "transaction" table
public class Transaction {

    @Id // Primary key of the transaction table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id;

    private String text; // Description of the transaction (e.g., "Groceries")

    private double amount; // Amount of the transaction (positive or negative)

    private LocalDate date; // Date when the transaction occurred

    @ManyToOne(fetch = FetchType.LAZY) // Many transactions belong to one user
    @JoinColumn(name = "user_id", nullable = false) // Foreign key referencing User entity
    @JsonBackReference // Prevents infinite recursion during JSON serialization
    private User user;
}
