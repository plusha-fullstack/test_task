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
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CommentResponseDTO createComment(CommentCreateDTO commentCreateDTO) {
        // Implementation similar to TaskService.addComment
        return null;
    }

    public CommentResponseDTO updateComment(CommentUpdateDTO commentUpdateDTO) {
        // Implementation similar to TaskService.updateComment
        return null;
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    public List<CommentResponseDTO> getAllComments(Long taskId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task != null) {
            List<Comment> comments = commentRepository.findByTask_Id(taskId);
            return comments.stream()
                    .map(comment -> modelMapper.map(comment, CommentResponseDTO.class))
                    .collect(Collectors.toList());
        }
        return null;
    }
}