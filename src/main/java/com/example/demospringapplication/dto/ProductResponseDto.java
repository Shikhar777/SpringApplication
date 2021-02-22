package com.example.demospringapplication.dto;

import java.util.List;

public class ProductResponseDto {

    private List<ProductDto> productDtoList;

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }

    public void setProductDtoList(List<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }
}
