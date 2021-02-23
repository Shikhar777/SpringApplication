package com.example.demospringapplication.service.impl;


import com.example.demospringapplication.client.SearchClient;
import com.example.demospringapplication.dto.ProductDto;
import com.example.demospringapplication.dto.ProductRequestDto;
import com.example.demospringapplication.dto.ProductResponseDto;
import com.example.demospringapplication.service.ProductInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class ProductService implements ProductInterface {

    @Autowired
    private SearchClient searchClient;

    @Override
    public ProductResponseDto searchProducts(ProductRequestDto productRequestDto)
    {
        Map<String, Object> productResponse = searchClient.getProducts(productRequestDto.getSearchTerm());
        List<Map<String, Object>> products = (List<Map<String,Object>>) ((Map<String, Object>) productResponse.get("response")).get("docs");
        ArrayList<ProductDto> list = new ArrayList<>();
        for (Map<String, Object> product : products) {
            ProductDto productDto = new ProductDto();
            productDto.setDescription((String) product.get("description"));
            productDto.setTitle((String) product.get("name"));
            productDto.setSalePrice((Double) product.get("salePrice"));
            //productDto.setStockLocation( product.get("stockLocation").toString());
            //productDto.setInStock((Integer) product.get("isInStock"));
            int res= (int) product.get("isInStock");
            if(res==1)
            {
                productDto.setInStock(true);
            }
            else
            {
                productDto.setInStock(false);
            }
            list.add(productDto);
        }

        Map<String, Object> productResponseLocation = searchClient.getProducts(productRequestDto.getGetStockLocation()+productRequestDto.getLocation());
        List<Map<String, Object>> productsLocation = (List<Map<String,Object>>) ((Map<String, Object>) productResponseLocation.get("response")).get("docs");
        ArrayList<ProductDto> listLocation = new ArrayList<>();
        for(Map<String, Object> product : productsLocation) {
            ProductDto productDto = new ProductDto();
            productDto.setDescription((String) product.get("description"));
            productDto.setTitle((String) product.get("name"));
            productDto.setSalePrice((Double) product.get("salePrice"));
            productDto.setLocation(product.get("stockLocation").toString());
            int res= (int) product.get("isInStock");
            if(res==1)
            {
                productDto.setInStock(true);
            }
            else
            {
                productDto.setInStock(false);
            }
            listLocation.add(productDto);
        }

        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setProductDtoList(list);
        responseDto.setLocationBasedProducts(listLocation);
        return responseDto;
    }
}
