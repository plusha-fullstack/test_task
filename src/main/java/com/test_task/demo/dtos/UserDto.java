package com.test_task.demo.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import com.test_task.demo.entities.Role;

import jakarta.persistence.*;
import java.util.Collection;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
}
