package com.user.task.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.task.model.User;





public interface UserRepository extends JpaRepository<User, Long> {
	
	
	    Optional<User> findByEmail(String email);

	    boolean existsByEmail(String email);

}
