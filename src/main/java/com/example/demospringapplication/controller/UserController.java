package com.example.demospringapplication.controller;

import com.example.demospringapplication.dto.MyRequestDto;
import com.example.demospringapplication.service.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UserController {
    public UserController(UserInterface userInterface) {
        this.userInterface = userInterface;
        System.out.println("Inside user controller constructor...");
    }

    @PostConstruct
    public void init()
    {
        System.out.println("Inside user controller post construct...");
    }

    @Autowired
    private UserInterface userInterface;

    @PostMapping(value="/update/employee/{id}")
    public boolean updateEmployee(@PathVariable String id, @RequestBody MyRequestDto request)
    {
        return userInterface.updateEmployee(request,id);
    }
}
