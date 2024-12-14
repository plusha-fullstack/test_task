package com.test_task.demo.dtos;

import lombok.Data;


@Data
public class UserDTO {
    private String username;

    private String password;

    private String email;
}


//package ru.flamexander.spring.security.jwt.dtos;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import ru.flamexander.spring.security.jwt.entities.Role;
//
//import javax.persistence.*;
//        import java.util.Collection;
//
//@Data
//@AllArgsConstructor
//public class UserDto {
//    private Long id;
//    private String username;
//    private String email;
//}

// commented one is from alexander!! Dto not DTO in his case