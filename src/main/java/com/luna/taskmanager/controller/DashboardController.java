package com.luna.taskmanager.controller;

import com.luna.taskmanager.model.User;
import com.luna.taskmanager.controller.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {
    private UserService userService;

    @Autowired
    public DashboardController(UserService userService) {
        this.userService = userService;
    }

    // Mapping for the registration page. It displays the registration form to the user.
    @GetMapping("/register")
    public ModelAndView showRegistrationForm(HttpServletResponse response) {
        clearResponseCache(response);
        return new ModelAndView("register");
    }

    // Endpoint to handle the user registration process.
    @PostMapping("/register")
    public ModelAndView registerUser(@RequestParam String username,
                                     @RequestParam String email,
                                     @RequestParam String password,
                                     @RequestParam String confirmPassword,
                                     HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("register");

        if (!password.equals(confirmPassword)) {
            modelAndView.addObject("error", "Passwords do not match.");
            return modelAndView;
        }

        if (password.length() < 8) {
            modelAndView.addObject("error", "Password must be at least 8 characters long.");
            return modelAndView;
        }

        // Check if username or email exists
        if (userService.checkUserExists(username) || userService.checkEmailExists(email)) {
            modelAndView.addObject("error", "Username or email already exists. Please try a different one.");
            return modelAndView;
        }

        User newUser = userService.registerUser(username, email, password);
        if (newUser != null) {
            clearResponseCache(response);
            return new ModelAndView("redirect:/");
        } else {
            modelAndView.addObject("error", "An error occurred during registration. Please try again.");
            return modelAndView;
        }
    }

    // Helper method to set response headers to prevent caching.
    private void clearResponseCache(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
    }
}