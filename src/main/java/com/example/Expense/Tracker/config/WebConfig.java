package com.example.Expense.Tracker.config;

/*
 * PURPOSE OF THIS FILE:
 * ----------------------
 * This configuration class customizes Spring MVC behavior for the application.
 * It provides:
 *   1. A default route that redirects the root URL ("/") to "login.html".
 *   2. CORS settings to allow the frontend (running on localhost:8080)
 *      to interact with the backend securely.
 *
 * In short: This class configures routing and CORS for the Expense Tracker API.
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;

@Configuration // Indicates that this class contains Spring configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(@NonNull ViewControllerRegistry registry) {
        /*
         * Redirects the root URL "/" to "login.html".
         * This ensures that when the user opens the base URL,
         * they are automatically taken to the login page.
         */
        registry.addViewController("/")
                .setViewName("forward:/login.html");
    }

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        /*
         * CORS Configuration:
         * Allows frontend requests (e.g., from a JavaScript SPA)
         * hosted on http://localhost:8080 to access backend APIs.
         *
         * Supports multiple HTTP methods and enables credential sharing.
         */
        registry.addMapping("/**") // Apply CORS to all API paths
                .allowedOrigins("http://localhost:8080") // Allowed frontend origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true); // Enable cookies/credentials if needed
    }
}
