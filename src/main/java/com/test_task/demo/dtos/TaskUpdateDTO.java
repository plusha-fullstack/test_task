package com.test_task.demo.dtos;

import lombok.Data;

@Data
public class TaskUpdateDTO {
    private String title;
    private String description;
    private String status;
    private String priority;
    private Long assigneeId;
}