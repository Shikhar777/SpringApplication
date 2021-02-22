package com.example.demospringapplication.service.impl;

import com.example.demospringapplication.dto.MyRequestDto;
import com.example.demospringapplication.service.UserInterface;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserServiceImpl implements UserInterface {

    public UserServiceImpl()
    {
        System.out.println("Inside user service constructor...");
    }

    @PostConstruct
    public void init()
    {
        System.out.println("Inside user service post construct...");
    }

    @Override
    public boolean updateEmployee(MyRequestDto request, String id)
    {
        System.out.println("Inside User Service: "+request+" "+id);
        return false;
    }
}
