package com.user.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.task.model.Task;
import com.user.task.model.User;
import com.user.task.repository.TaskRepository;
import com.user.task.repository.UserRepository;
@Service
public class TaskServiceIml implements TaskService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	@Override
	public Task assignTask(Long userId, Task task) {
		// TODO Auto-generated method stub
		 User user = userRepository.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found"));
		 
		 task.setAssignedTo(user);
		return taskRepository.save(task);
	}

	@Override
	public List<Task> getUserTasks(Long userId) {
		// TODO Auto-generated method stub
		  User user = userRepository.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        return user.getUserTask();
	}
	
	
	

}
