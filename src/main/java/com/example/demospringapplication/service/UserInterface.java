package com.example.demospringapplication.service;

import com.example.demospringapplication.dto.MyRequestDto;

public interface UserInterface {

    boolean updateEmployee(MyRequestDto request, String id);
}
