package com.example.demospringapplication.dto;

import java.util.List;

public class ProductResponseDto {

    private List<ProductDto> productDtoList;
    private List<ProductDto> locationBasedProducts;

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }

    public void setProductDtoList(List<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }

    public List<ProductDto> getLocationBasedProducts() {
        return locationBasedProducts;
    }

    public void setLocationBasedProducts(List<ProductDto> locationBasedProducts) {
        this.locationBasedProducts = locationBasedProducts;
    }
}
