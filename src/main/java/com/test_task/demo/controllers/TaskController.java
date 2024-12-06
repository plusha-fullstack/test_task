package com.test_task.demo.controllers;

import com.test_task.demo.dtos.*;

import com.test_task.demo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Create a task
    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskCreateDTO taskCreateDTO) {
        TaskResponseDTO task = taskService.createTask(taskCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    // Update a task
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @RequestBody TaskUpdateDTO taskUpdateDTO) {
        TaskResponseDTO task = taskService.updateTask(id, taskUpdateDTO);
        return ResponseEntity.ok(task);
    }

    // Delete a task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    // Get a task by ID
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        TaskResponseDTO task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    // Get all tasks with filtering and pagination
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks(
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Long assigneeId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String priority,
            Pageable pageable) {
        List<TaskResponseDTO> tasks = (List<TaskResponseDTO>) taskService.getAllTasks(authorId, assigneeId, status, priority, pageable);
        return ResponseEntity.ok(tasks);
    }

    // Assign a task to a user
    @PutMapping("/{id}/assignee")
    public ResponseEntity<TaskResponseDTO> assignTask(@PathVariable Long id, @RequestParam Long assigneeId) {
        TaskResponseDTO task = taskService.assignTask(id, assigneeId);
        return ResponseEntity.ok(task);
    }

    // Add a comment to a task
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentResponseDTO> addComment(@PathVariable Long id, @RequestBody CommentCreateDTO commentCreateDTO) {
        CommentResponseDTO comment = taskService.addComment(id, commentCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    // Update a comment
    @PutMapping("/{taskId}/comments/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateComment(@PathVariable Long taskId, @PathVariable Long commentId, @RequestBody CommentUpdateDTO commentUpdateDTO) {
        CommentResponseDTO comment = taskService.updateComment(taskId, commentId, commentUpdateDTO);
        return ResponseEntity.ok(comment);
    }

    // Delete a comment
    @DeleteMapping("/{taskId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long taskId, @PathVariable Long commentId) {
        taskService.deleteComment(taskId, commentId);
        return ResponseEntity.noContent().build();
    }

    // Get all comments for a task
    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentResponseDTO>> getAllComments(@PathVariable Long id) {
        List<CommentResponseDTO> comments = taskService.getAllComments(id);
        return ResponseEntity.ok(comments);
    }
}