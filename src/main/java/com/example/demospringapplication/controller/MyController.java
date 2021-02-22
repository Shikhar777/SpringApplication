package com.example.demospringapplication.controller;

import com.example.demospringapplication.dto.MyRequestDto;
import com.example.demospringapplication.dto.MyResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyController {

    @GetMapping(value="/hello")
    public String helloWorld()
    {
        return "Success!";
    }

    @PostMapping(value="/hello-post")
    public String helloWorldPost()
    {
        return "Success-Post!!";
    }

    @GetMapping(value="/hello-query")
    public String helloQuery(@RequestParam String query)
    {
        return ("Query: "+query);
    }

    @PostMapping(value="/register")
    public String registerUser(@RequestBody  MyRequestDto request)
    {
        return request.toString();
    }

    @GetMapping(value = "/employee/{employeeId}")
    public MyResponseDto findById(@PathVariable String employeeId)
    {
        MyResponseDto obj = new MyResponseDto();
        obj.setId(employeeId);
        return obj;
    }
}
