package com.example.demospringapplication.service.impl;


import com.example.demospringapplication.client.SearchClient;
import com.example.demospringapplication.dto.ProductDto;
import com.example.demospringapplication.dto.ProductRequestDto;
import com.example.demospringapplication.dto.ProductResponseDto;
import com.example.demospringapplication.service.ProductInterface;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;


@Service
public class ProductService implements ProductInterface {

    private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private SearchClient searchClient;

    @Override
    public ProductResponseDto getProducts(ProductRequestDto productRequestDto)
    {
        ProductResponseDto responseDto = new ProductResponseDto();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable runnable1 = () -> {
            LOG.info("started search term products");
            Map<String, Object> productResponse = searchClient.getProducts(productRequestDto.getSearchTerm());
            List<Map<String, Object>> products = (List<Map<String, Object>>) ((Map<String, Object>) productResponse.get("response")).get("docs");
            ArrayList<ProductDto> list = new ArrayList<>();
            for (Map<String, Object> product : products) {
                ProductDto productDto = new ProductDto();
                productDto.setDescription((String) product.get("description"));
                productDto.setTitle((String) product.get("name"));
                productDto.setSalePrice((Double) product.get("salePrice"));
                int res = (int) product.get("isInStock");
                if (res == 1) {
                    productDto.setInStock(true);
                } else {
                    productDto.setInStock(false);
                }
                list.add(productDto);
            }
            LOG.info("finished search term products");
            responseDto.setProductDtoList(list);
        };

        Runnable runnable2 = () -> {
            LOG.info("started location products");
            Map<String, Object> productResponseLocation = searchClient.getProducts(productRequestDto.getGetStockLocation() + productRequestDto.getLocation());
            List<Map<String, Object>> productsLocation = (List<Map<String, Object>>) ((Map<String, Object>) productResponseLocation.get("response")).get("docs");
            ArrayList<ProductDto> listLocation = new ArrayList<>();
            for (Map<String, Object> product : productsLocation) {
                ProductDto productDto = new ProductDto();
                productDto.setDescription((String) product.get("description"));
                productDto.setTitle((String) product.get("name"));
                productDto.setSalePrice((Double) product.get("salePrice"));
                int res = (int) product.get("isInStock");
                if (res == 1) {
                    productDto.setInStock(true);
                } else {
                    productDto.setInStock(false);
                }
                listLocation.add(productDto);
            }
            responseDto.setLocationBasedProducts(listLocation);
            LOG.info("finished location products");
        };
        executorService.execute(runnable1);
        executorService.execute(runnable2);
        executorService.shutdown();
        try {
            executorService.awaitTermination(60, TimeUnit.SECONDS);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        return responseDto;
    }
}
