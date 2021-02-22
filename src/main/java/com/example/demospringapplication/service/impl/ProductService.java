package com.example.demospringapplication.service.impl;


import com.example.demospringapplication.dto.ProductDto;
import com.example.demospringapplication.dto.ProductRequestDto;
import com.example.demospringapplication.dto.ProductResponseDto;
import com.example.demospringapplication.service.ProductInterface;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class ProductService implements ProductInterface {

    @Override
    public ProductResponseDto searchProducts(ProductRequestDto productRequestDto)
    {
        ProductResponseDto response = new ProductResponseDto();
        ProductDto productDto = new ProductDto();
        productDto.setTitle("Samsung");
        productDto.setInStock(true);
        productDto.setSalePrice(10000);
        productDto.setDescription("New Samsung Smart Series Mobile");
        response.setProductDtoList(Arrays.asList(productDto));
        return response;
    }

}
