package com.test_task.demo.dtos;

import lombok.Data;

@Data
public class JWTResponseDTO {
    private String token;
    private UserResponseDTO user;
}