package com.test_task.demo.dtos;

import lombok.Data;

@Data
public class CommentCreateDTO {
    private Long taskId;
    private String text;
}