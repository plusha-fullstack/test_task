package com.test_task.demo.dtos;

import lombok.Data;

@Data
public class TaskFilterDTO {
    private Long authorId;
    private Long assigneeId;
    private String status;
    private String priority;
    private Integer page;
    private Integer size;
}