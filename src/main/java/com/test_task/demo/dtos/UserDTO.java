package com.test_task.demo.dtos;

import lombok.Data;


@Data
public class UserDTO {
    private String username;

    private String password;

    private String email;
}