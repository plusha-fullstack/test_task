package com.test_task.demo.dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CommentResponseDTO {
    private Long id;
    private Long taskId;
    private UserResponseDTO author;
    private String text;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}