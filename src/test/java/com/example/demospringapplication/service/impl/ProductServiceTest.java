package com.example.demospringapplication.service.impl;

import com.example.demospringapplication.client.SearchClient;
import com.example.demospringapplication.dto.ProductRequestDto;
import com.example.demospringapplication.dto.ProductResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private SearchClient searchClient;

    @BeforeEach
    public void init()
    {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown()
    {

    }

    @Test
    void searchProducts() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> searchTermMockObject = objectMapper.readValue(new
                URL("file:src/test/resources/search.mock"), Map.class);
        Map<String, Object> locationMockObject = objectMapper.readValue(new
                URL("file:src/test/resources/location.mock"), Map.class);
        Mockito.when(searchClient.getProducts("Samsung")).thenReturn(searchTermMockObject);
        Mockito.when(searchClient.getProducts("stockLocation:Jakarta")).thenReturn(locationMockObject);
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setSearchTerm("Samsung");
        requestDto.setLocation("Jakarta");
        ProductResponseDto responseDto = productService.getProducts(requestDto);
        assertEquals(responseDto.getProductDtoList().size(), 10);
        assertEquals(responseDto.getLocationBasedProducts().size(), 10);
        Mockito.verify(searchClient).getProducts("Samsung");
        Mockito.verify(searchClient).getProducts("stockLocation:Jakarta");
    }

    @Test
    void testGetProductsException() throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> searchTermMockObject = objectMapper.readValue(new
                URL("file:src/test/resources/search.mock"), Map.class);
        Map<String, Object> locationMockObject = objectMapper.readValue(new
                URL("file:src/test/resources/location.mock"), Map.class);
        Mockito.when(searchClient.getProducts("Samsung")).thenReturn(searchTermMockObject);
        Mockito.when(searchClient.getProducts("stockLocation:Jakarta")).thenThrow(NullPointerException.class);
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setSearchTerm("Samsung");
        requestDto.setLocation("Jakarta");
        ProductResponseDto responseDto = productService.getProducts(requestDto);
        assertEquals(responseDto.getProductDtoList().size(), 10);
        assertEquals(responseDto.getLocationBasedProducts(), null);
        Mockito.verify(searchClient).getProducts("Samsung");
        Mockito.verify(searchClient).getProducts("stockLocation:Jakarta");
    }
}