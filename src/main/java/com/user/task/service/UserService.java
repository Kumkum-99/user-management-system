package com.user.task.service;

import java.util.List;

import com.user.task.dto.UserRequestDTO;
import com.user.task.dto.UserResponseDTO;


public interface UserService {
	
	UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long id);
    UserResponseDTO updateUser(Long id, UserRequestDTO userRuRequestDTO);
    void deleteUser(Long id);
    UserResponseDTO assignRole(Long userId, String roleName);
	 
	

}
