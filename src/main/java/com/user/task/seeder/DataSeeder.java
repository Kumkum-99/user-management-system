package com.user.task.seeder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.user.task.model.Role;
import com.user.task.model.User;
import com.user.task.repository.RoleRepository;
import com.user.task.repository.UserRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        //  Create roles
        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName("ROLE_ADMIN");
                    return roleRepository.save(role);
                });

        Role managerRole = roleRepository.findByName("ROLE_MANAGER")
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName("ROLE_MANAGER");
                    return roleRepository.save(role);
                });

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName("ROLE_USER");
                    return roleRepository.save(role);
                });

        //  Create Admin
        if (!userRepository.existsByEmail("admin@gmail.com")) {
            User admin = new User();
            admin.setName("Admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setRoles(List.of(adminRole));

            userRepository.save(admin);
        }

        //  Create Manager
        if (!userRepository.existsByEmail("manager@gmail.com")) {
            User manager = new User();
            manager.setName("Manager");
            manager.setEmail("manager@gmail.com");
            manager.setPassword(passwordEncoder.encode("123456"));
            manager.setRoles(List.of(managerRole));

            userRepository.save(manager);
        }
        //create user
        if (!userRepository.existsByEmail("user@gmail.com")) {
            User manager = new User();
            manager.setName("User");
            manager.setEmail("user@gmail.com");
            manager.setPassword(passwordEncoder.encode("123456"));
            manager.setRoles(List.of(userRole));

            userRepository.save(manager);
        }
    }
}