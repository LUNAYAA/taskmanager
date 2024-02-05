package com.luna.taskmanager.controller.service;

import com.luna.taskmanager.model.User;
import com.luna.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // CHECK IF the USERNAME IS TAKEN
    public boolean checkUserExists(String username) {
        return userRepository.existsByUsername(username);
    }
    // Register user
    public User registerUser(String username, String email, String password) {
        if (!checkUserExists(username)) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPassword(passwordEncoder.encode(password));
            userRepository.save(newUser);
            return newUser;
        }
        return null;
    }

    // check email address is available
    public boolean checkEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }
}