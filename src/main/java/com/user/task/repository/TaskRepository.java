package com.user.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.task.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
