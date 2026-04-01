package com.user.task.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.task.dto.UserRequestDTO;
import com.user.task.dto.UserResponseDTO;
import com.user.task.model.Role;
import com.user.task.model.User;
import com.user.task.repository.RoleRepository;
import com.user.task.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Override
	public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
		// TODO Auto-generated method stub
		if(userRepo.existsByEmail(userRequestDTO.getEmail())) {
			throw new RuntimeException("User with this email Already exist");
		}
		
		User user=mapper.map(userRequestDTO, User.class);
		user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
		User saved=userRepo.save(user);
		
		return mapper.map(saved, UserResponseDTO.class);
	}


	@Override
	public List<UserResponseDTO> getAllUsers() {
		// TODO Auto-generated method stub
		
		
		return userRepo.findAll()
				.stream()
				.map(user -> mapper.map(user, UserResponseDTO.class))
				.toList();
	}


	@Override
	public UserResponseDTO getUserById(Long id) {
		// TODO Auto-generated method stub
		User user=userRepo.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
		
		return mapper.map(user, UserResponseDTO.class);
	}


	@Override
	public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
		// TODO Auto-generated method stub
		User user=userRepo.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
		user.setEmail(userRequestDTO.getEmail());
		user.setName(userRequestDTO.getName());
		User updated=userRepo.save(user);
		return mapper.map(updated, UserResponseDTO.class);
	}


	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		User user=userRepo.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
		userRepo.deleteById(id);
		
	}


	@Override
	public UserResponseDTO assignRole(Long userId, String roleName) {
		// TODO Auto-generated method stub
		
		User user=userRepo.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
		 Role role = roleRepository.findByName(roleName)
	                .orElseThrow(() -> new RuntimeException("Role not found"));
		  user.getRoles().add(role);
		  User updated = userRepo.save(user);

	     return mapper.map(updated, UserResponseDTO.class);
	}

	

	

	
	
	 

	

}
