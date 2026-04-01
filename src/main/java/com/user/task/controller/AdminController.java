package com.user.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.task.dto.UserRequestDTO;
import com.user.task.dto.UserResponseDTO;
import com.user.task.service.UserServiceImpl;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')") 

public class AdminController {

    @Autowired
    private UserServiceImpl userService;

    //  Create user
    @PostMapping("/create")
    public UserResponseDTO createUser(@RequestBody UserRequestDTO dto) {
        return userService.createUser(dto);
    }
    

    //  Get all users
    @GetMapping("/users")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    //  Delete user
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted";
    }

    //  Assign role
    @PostMapping("/assign-role")
    public UserResponseDTO assignRole(@RequestParam Long userId,
                                      @RequestParam String role) {
        return userService.assignRole(userId, role);
    }
}