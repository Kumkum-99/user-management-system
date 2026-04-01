package com.user.task.service;

import java.util.List;

import com.user.task.model.Task;

public interface TaskService {
	Task assignTask(Long userId, Task task);
    List<Task> getUserTasks(Long userId);

}
