package com.user.task.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.task.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(String roleName);

}
