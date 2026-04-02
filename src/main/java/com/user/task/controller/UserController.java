package com.user.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.task.model.Task;
import com.user.task.service.TaskServiceIml;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TaskServiceIml taskService;

    // View own tasks
    @GetMapping("/tasks/{userId}")
    public List<Task> getTasks(@PathVariable Long userId) {
        return taskService.getUserTasks(userId);
    }
}

