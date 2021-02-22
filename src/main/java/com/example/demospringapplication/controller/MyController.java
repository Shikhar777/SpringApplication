package com.example.demospringapplication.controller;


import com.example.demospringapplication.dto.ProductRequestDto;
import com.example.demospringapplication.dto.ProductResponseDto;
import com.example.demospringapplication.service.ProductInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private ProductInterface productInterface;

    @PostMapping(value="/search")
    public ProductResponseDto searchProducts(@RequestBody ProductRequestDto productRequestDto)
    {
        return productInterface.searchProducts(productRequestDto);
    }
}
