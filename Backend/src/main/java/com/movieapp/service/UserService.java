package com.movieapp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.movieapp.config.JwtUtil;
import com.movieapp.entity.User;
import com.movieapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(User user) {

        User existing = userRepository.findByEmail(user.getEmail());

        if (existing != null) {
            return "Email already exists";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return "User Registered Successfully";
    }

    public String login(String email, String password) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            return "User not found";
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "Invalid password";
        }

        return jwtUtil.generateToken(email);
    }
}