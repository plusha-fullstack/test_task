package com.test_task.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ActionsController {

    @GetMapping("/addtask")
    public String addTask(){
        return  "task added";
    }
    @GetMapping("/edittask")
    public String editTask(){
        return  "task edited";
    }

}
