package com.tasky.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tasky.api.entity.Task;
import com.tasky.api.repository.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository p) {
        this.taskRepository = p;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task savTask(Task p) {
        return taskRepository.save(p);
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }
}
