package com.test_task.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.modelmapper.ModelMapper;
import com.test_task.demo.entities.*;
import com.test_task.demo.dtos.*;
import com.test_task.demo.controllers.*;
import com.test_task.demo.repositories.*;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TaskResponseDTO createTask(TaskCreateDTO taskCreateDTO) {
        Task task = modelMapper.map(taskCreateDTO, Task.class);
        // Set author based on current user (to be implemented with security)
        User author = userRepository.findById(1L).orElse(null);
        task.setAuthor(author);
        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask, TaskResponseDTO.class);
    }

    public TaskResponseDTO updateTask(Long id, TaskUpdateDTO taskUpdateDTO) {
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask != null) {
            modelMapper.map(taskUpdateDTO, existingTask);
            Task updatedTask = taskRepository.save(existingTask);
            return modelMapper.map(updatedTask, TaskResponseDTO.class);
        }
        return null;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public TaskResponseDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            TaskResponseDTO taskResponseDTO = modelMapper.map(task, TaskResponseDTO.class);
            List<Comment> comments = commentRepository.findByTask_Id(id);
            taskResponseDTO.setComments(comments.stream()
                    .map(comment -> modelMapper.map(comment, CommentResponseDTO.class))
                    .collect(Collectors.toList()));
            return taskResponseDTO;
        }
        return null;
    }

    public Page<TaskResponseDTO> getAllTasks(Long authorId, Long assigneeId, String status, String priority, Pageable pageable) {
        Page<Task> tasks = taskRepository.findByAuthor_IdOrAssignee_IdAndStatusAndPriority(authorId, assigneeId, status, priority, pageable);
        // Map tasks to TaskResponseDTO
        // Fetch and set comments for each task
        return null; // Implementation to be completed
    }

    public TaskResponseDTO assignTask(Long id, Long assigneeId) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            User assignee = userRepository.findById(assigneeId).orElse(null);
            task.setAssignee(assignee);
            Task updatedTask = taskRepository.save(task);
            return modelMapper.map(updatedTask, TaskResponseDTO.class);
        }
        return null;
    }

    public CommentResponseDTO addComment(Long taskId, CommentCreateDTO commentCreateDTO) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task != null) {
            Comment comment = modelMapper.map(commentCreateDTO, Comment.class);
            // Set author based on current user (to be implemented with security)
            User author = userRepository.findById(1L).orElse(null);
            comment.setAuthor(author);
            comment.setTask(task);
            Comment savedComment = commentRepository.save(comment);
            return modelMapper.map(savedComment, CommentResponseDTO.class);
        }
        return null;
    }

    public CommentResponseDTO updateComment(Long taskId, Long commentId, CommentUpdateDTO commentUpdateDTO) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment != null && comment.getTask().getId().equals(taskId)) {
            modelMapper.map(commentUpdateDTO, comment);
            Comment updatedComment = commentRepository.save(comment);
            return modelMapper.map(updatedComment, CommentResponseDTO.class);
        }
        return null;
    }

    public void deleteComment(Long taskId, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment != null && comment.getTask().getId().equals(taskId)) {
            commentRepository.deleteById(commentId);
        }
    }

    public List<CommentResponseDTO> getAllComments(Long id) {
        List<Comment> comments = commentRepository.findByTask_Id(id);
        return comments.stream()
                .map(comment -> modelMapper.map(comment, CommentResponseDTO.class))
                .collect(Collectors.toList());
    }
}