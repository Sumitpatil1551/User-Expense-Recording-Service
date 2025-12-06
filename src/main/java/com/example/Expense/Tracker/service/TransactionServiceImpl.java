package com.example.Expense.Tracker.service;

/*
 * PURPOSE OF THIS FILE:
 * ----------------------
 * This service class provides the business logic for handling transactions
 * in the Expense Tracker system. It supports adding new transactions,
 * retrieving all transactions for a specific user, and deleting a transaction
 * while ensuring proper user ownership validation.
 *
 * In short: This class manages all transaction-related operations for users.
 */

import com.example.Expense.Tracker.model.Transaction;
import com.example.Expense.Tracker.model.User;
import com.example.Expense.Tracker.repository.TransactionRepository;
import com.example.Expense.Tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Marks this class as a Spring service component
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository; // Handles DB operations for transactions

    @Autowired
    private UserRepository userRepository; // Used to fetch user details from DB

    @Override
    public Transaction addTransaction(Transaction transaction, String username) {
        // Fetch the user who is adding the transaction
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found with username: " + username);
        }

        // Attach the user to the transaction before saving
        transaction.setUser(user);
        return transactionRepository.save(transaction); // Persist transaction in DB
    }

    @Override
    public List<Transaction> getAllTransactions(String username) {
        // Fetch all transactions belonging to the given user
        return transactionRepository.findByUserUsername(username);
    }

    @Override
    public void deleteTransaction(Long id, String username) {
        // Validate that the transaction exists AND belongs to the given user
        Transaction transaction = transactionRepository.findByIdAndUserUsername(id, username);

        if (transaction != null) {
            transactionRepository.delete(transaction); // Safe deletion
        } else {
            throw new RuntimeException(
                    "Transaction not found or does not belong to the user: " + username
            );
        }
    }
}
