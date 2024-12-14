package com.test_task.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.test_task.demo.dtos.JwtRequest;
import com.test_task.demo.dtos.RegistrationUserDto;
import com.test_task.demo.services.AuthService;


// this controller by alex
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return authService.createNewUser(registrationUserDto);
    }
}

//{
//        "title": "Test Task",
//        "description": "This is a test task.",
//        "status": "Pending",
//        "priority": "High",
//        "assigneeId": 1 ,
//        "authorId": 2
//        }
