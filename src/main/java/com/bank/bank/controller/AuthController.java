package com.bank.bank.controller;

import com.bank.bank.model.User;
import com.bank.bank.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtils;
    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/token")
    public String authenticateUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtils.generateToken(userDetails.getUsername(),user.getRole());
    }
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        //if (userRepository.existsByUsername(user.getUsername())) {
        //    return "Error: Username is already taken!";
        //}
        // Create new user's account
        /*User newUser = new User(
                null,
                user.getUsername(),
                encoder.encode(user.getPassword()),
                user.getRole()
        );
        userRepository.save(newUser);*/
        return "User registered successfully!";
    }
}
