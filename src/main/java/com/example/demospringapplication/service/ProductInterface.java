package com.example.demospringapplication.service;

import com.example.demospringapplication.dto.ProductRequestDto;
import com.example.demospringapplication.dto.ProductResponseDto;

public interface ProductInterface {

    ProductResponseDto searchProducts(ProductRequestDto productRequestDto);
}
