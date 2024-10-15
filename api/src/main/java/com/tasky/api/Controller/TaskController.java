package com.tasky.api.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasky.api.entity.Task;
import com.tasky.api.service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService s) {
        taskService = s;
    }

    @GetMapping
    public List<Task> gettAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> p = taskService.getTaskById(id);

        if (p.isPresent()) {
            return ResponseEntity.ok(p.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public Task saveTask(@RequestBody Task task) {
        return taskService.savTask(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {

        Optional<Task> p = taskService.getTaskById(id);

        if (p.isPresent()) {

            Task updatedTask = p.get();
            updatedTask.setDescription(task.getDescription());
            updatedTask.setName(task.getName());
            updatedTask.setDueDate(task.getDueDate());
            updatedTask.setIsCompleted(task.getIsCompleted());
            return ResponseEntity.ok(taskService.savTask(updatedTask));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

}
