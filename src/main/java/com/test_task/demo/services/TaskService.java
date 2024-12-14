package com.test_task.demo.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.modelmapper.ModelMapper;
import com.test_task.demo.entities.*;
import com.test_task.demo.dtos.*;
import com.test_task.demo.controllers.*;
import com.test_task.demo.repositories.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.security.Principal;

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

        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        System.out.println(currentUsername); // admin

        // Fetch the user from the database
        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("User not found in DB"));


        System.out.println(currentUser.getRoles()); // abracadabra
        // not sure admin or role_admin!!!
        if (currentUser.getRoles().stream().noneMatch(role -> "ROLE_ADMIN".equals(role.getName()))) {
            System.out.println(currentUser.getRoles());
            throw new AccessDeniedException("Only admins can assign tasks to other users.");
        }

        Task task = modelMapper.map(taskCreateDTO, Task.class);
        task.setAuthor(currentUser);
        task.setAssignee(
                userRepository.findById(taskCreateDTO.getAssigneeId())
                        .orElseThrow(() -> new RuntimeException("Assignee not found with ID: " + taskCreateDTO.getAssigneeId()))
        );
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());


        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask, TaskResponseDTO.class);
//        Task task = modelMapper.map(taskCreateDTO, Task.class);
//        // Set author based on current user (to be implemented with security)
//        User author = userRepository.findById(1L).orElse(null);
//        task.setAuthor(author);
//        Task savedTask = taskRepository.save(task);
//        return modelMapper.map(savedTask, TaskResponseDTO.class);

    }
    } // this close whole class!! to remove !

//    public TaskResponseDTO updateTask(Long id, TaskUpdateDTO taskUpdateDTO) {  all this stuff lATER!
//        Task existingTask = taskRepository.findById(id).orElse(null);
//        if (existingTask != null) {
//            modelMapper.map(taskUpdateDTO, existingTask);
//            Task updatedTask = taskRepository.save(existingTask);
//            return modelMapper.map(updatedTask, TaskResponseDTO.class);
//        }
//        return null;
//    }
//
//    public void deleteTask(Long id) {
//        taskRepository.deleteById(id);
//    }
//
//    public TaskResponseDTO getTaskById(Long id) {
//        Task task = taskRepository.findById(id).orElse(null);
//        if (task != null) {
//            TaskResponseDTO taskResponseDTO = modelMapper.map(task, TaskResponseDTO.class);
//            List<Comment> comments = commentRepository.findByTask_Id(id);
//            taskResponseDTO.setComments(comments.stream()
//                    .map(comment -> modelMapper.map(comment, CommentResponseDTO.class))
//                    .collect(Collectors.toList()));
//            return taskResponseDTO;
//        }
//        return null;
//    }
//
//    public Page<TaskResponseDTO> getAllTasks(Long authorId, Long assigneeId, String status, String priority, Pageable pageable) {
//        Page<Task> tasks = taskRepository.findByAuthor_IdOrAssignee_IdAndStatusAndPriority(authorId, assigneeId, status, priority, pageable);
//        // Map tasks to TaskResponseDTO
//        // Fetch and set comments for each task
//        return null; // Implementation to be completed
//    }
//
//    public TaskResponseDTO assignTask(Long id, Long assigneeId) {
//        Task task = taskRepository.findById(id).orElse(null);
//        if (task != null) {
//            User assignee = userRepository.findById(assigneeId).orElse(null);
//            task.setAssignee(assignee);
//            Task updatedTask = taskRepository.save(task);
//            return modelMapper.map(updatedTask, TaskResponseDTO.class);
//        }
//        return null;
//    }
//
//    public CommentResponseDTO addComment(Long taskId, CommentCreateDTO commentCreateDTO) {
//        Task task = taskRepository.findById(taskId).orElse(null);
//        System.out.println(task);
//        if (task != null) {
//            Comment comment = modelMapper.map(commentCreateDTO, Comment.class);
//            // Set author based on current user (to be implemented with security)
//
//
//
//            User author = userRepository.findById(1L).orElse(null);
//            comment.setText("This is init comment");
//            comment.setAuthor(author);
//            comment.setTask(task);
//            comment.setCreatedAt(LocalDateTime.now());
//            comment.setUpdatedAt(LocalDateTime.now());
//            Comment savedComment = commentRepository.save(comment);
//            return modelMapper.map(savedComment, CommentResponseDTO.class);
//        }
//        return null;
//    }
//
//    public CommentResponseDTO updateComment(Long taskId, Long commentId, CommentUpdateDTO commentUpdateDTO) {
//        Comment comment = commentRepository.findById(commentId).orElse(null);
//        if (comment != null && comment.getTask().getId().equals(taskId)) {
//            modelMapper.map(commentUpdateDTO, comment);
//            Comment updatedComment = commentRepository.save(comment);
//            return modelMapper.map(updatedComment, CommentResponseDTO.class);
//        }
//        return null;
//    }
//
//    public void deleteComment(Long taskId, Long commentId) {
//        Comment comment = commentRepository.findById(commentId).orElse(null);
//        if (comment != null && comment.getTask().getId().equals(taskId)) {
//            commentRepository.deleteById(commentId);
//        }
//    }
//
//    public List<CommentResponseDTO> getAllComments(Long id) {
//        List<Comment> comments = commentRepository.findByTask_Id(id);
//        return comments.stream()
//                .map(comment -> modelMapper.map(comment, CommentResponseDTO.class))
//                .collect(Collectors.toList());
//    }
//}