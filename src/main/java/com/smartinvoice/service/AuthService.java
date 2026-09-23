package com.smartinvoice.service;

import com.smartinvoice.dao.UserDAO;
import com.smartinvoice.model.User;
import com.smartinvoice.utils.PasswordHasher;

public class AuthService {

    private final UserDAO userDAO = new UserDAO();

    // Stores the currently logged-in user
    private User loggedInUser;

    // ================= REGISTER USER =================

    public String registerUser(User user, String confirmPassword) {

        if (user.getFullName().isBlank()
                || user.getEmail().isBlank()
                || user.getPhone().isBlank()
                || user.getPassword().isBlank()) {

            return "Please fill all fields.";
        }

        if (!user.getPassword().equals(confirmPassword)) {
            return "Passwords do not match.";
        }

        if (userDAO.emailExists(user.getEmail())) {
            return "Email already exists.";
        }

        // Every new registered user is an employee
        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("EMPLOYEE");
        }

        // Encrypt password
        user.setPassword(
                PasswordHasher.hashPassword(user.getPassword())
        );

        if (userDAO.registerUser(user)) {
            return "Registration Successful.";
        }

        return "Registration Failed.";
    }

    // ================= LOGIN USER =================

    public String loginUser(String email, String password) {

        if (email.isBlank() || password.isBlank()) {
            return "Email and Password are required.";
        }

        User user = userDAO.loginUser(email);

        if (user == null) {
            return "User not found.";
        }

        if (!PasswordHasher.verifyPassword(password, user.getPassword())) {
            return "Invalid Password";
        }

        // Save logged-in user
        loggedInUser = user;

        return "Login Successful";
    }

    // ================= LOGGED-IN USER =================

    public User getLoggedInUser() {
        return loggedInUser;
    }

    // ================= ROLE CHECK =================

    public boolean isAdmin() {

        return loggedInUser != null
                && "ADMIN".equalsIgnoreCase(loggedInUser.getRole());

    }

    public boolean isEmployee() {

        return loggedInUser != null
                && "EMPLOYEE".equalsIgnoreCase(loggedInUser.getRole());

    }

    // ================= LOGOUT =================

    public void logout() {
        loggedInUser = null;
    }

}