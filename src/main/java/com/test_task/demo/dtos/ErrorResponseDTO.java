package com.test_task.demo.dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ErrorResponseDTO {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}