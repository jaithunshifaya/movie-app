package com.movieapp.controller;

import com.movieapp.entity.User;
import com.movieapp.repository.UserRepository;
import com.movieapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {

        String token = userService.login(user.getEmail(), user.getPassword());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return response;
    }


}