package com.example.Expense.Tracker.controller;

/*
 * PURPOSE OF THIS FILE:
 * ----------------------
 * This REST controller exposes endpoints for managing user transactions.
 * It allows clients to:
 *   - Add a new transaction for a specific user
 *   - Retrieve all transactions belonging to a user
 *   - Delete a specific transaction while ensuring ownership validation
 *
 * In short: This class handles all HTTP requests related to transactions.
 */

import com.example.Expense.Tracker.model.Transaction;
import com.example.Expense.Tracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST controller returning JSON responses
@RequestMapping("/ExpTrack/transactions") // Base URL for all transaction-related APIs
@CrossOrigin // Enables CORS for frontend communication
public class TransactionController {

    @Autowired
    private TransactionService transactionService; // Service handling business logic

    // -------------------- API: Add Transaction --------------------

    /*
     * Adds a new transaction for a given username.
     * The transaction details come from the request body,
     * while the username is supplied via the URL path.
     */
    @PostMapping("/{username}")
    public Transaction addTransaction(@RequestBody Transaction transaction,
                                      @PathVariable String username) {
        return transactionService.addTransaction(transaction, username);
    }

    // -------------------- API: Get All Transactions --------------------

    /*
     * Returns all transactions associated with the specified username.
     * Useful for displaying the user's transaction history on the frontend.
     */
    @GetMapping("/{username}")
    public List<Transaction> getAllTransactions(@PathVariable String username) {
        return transactionService.getAllTransactions(username);
    }

    // -------------------- API: Delete Transaction --------------------

    /*
     * Deletes a specific transaction using its ID,
     * but only if it belongs to the given username.
     */
    @DeleteMapping("/{username}/{id}")
    public void deleteTransaction(@PathVariable String username,
                                  @PathVariable Long id) {
        transactionService.deleteTransaction(id, username);
    }
}
