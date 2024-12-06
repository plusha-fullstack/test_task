package com.test_task.demo.dtos;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private UserResponseDTO author;
    private UserResponseDTO assignee;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentResponseDTO> comments;
}