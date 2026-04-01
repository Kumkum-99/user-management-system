package com.user.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.task.dto.LoginDTO;
import com.user.task.model.User;
import com.user.task.repository.UserRepository;
import com.user.task.utils.JwtUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );

        //  get user role from DB
        User user = userRepository.findByEmail(dto.getEmail()).get();

        String role = user.getRoles().get(0).getName(); // first role

        return jwtUtils.generateToken(dto.getEmail(), role);
    }
}
