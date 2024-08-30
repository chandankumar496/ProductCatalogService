package com.chandan.productcatalogservice.controllers;

import com.chandan.productcatalogservice.dtos.ProductDto;
import com.chandan.productcatalogservice.models.Product;
import com.chandan.productcatalogservice.services.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductsController.class)
public class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void Test_GetAllProducts_RunsSuccessfully() throws Exception {

        //Arrange
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setName("Iphone15");
        products.add(product);
        product = new Product();
        product.setName("Iphone13");
        products.add(product);

        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(objectMapper.writeValueAsString(products)))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Iphone15"))
                .andExpect(jsonPath("$[1].name").value("Iphone13"));

    }

    @Test
    public void Test_CreateProduct_ProductCreatedSuccessfully() throws Exception {

        Product expectedProduct = new Product();
        expectedProduct.setName("MacBook");
       // expectedProduct.setId(2L);

        ProductDto productToCreate = new ProductDto();
        productToCreate.setName("MacBook");
        //productToCreate.setId(2L);

        when(productService.createProduct(any(Product.class))).thenReturn(expectedProduct);

       // mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON).content(
         //       objectMapper.writeValueAsString(productToCreate)))
           //     .andExpect(status().isOk())
             //   .andExpect(content().string(objectMapper.writeValueAsString(expectedProduct)))
               // .andExpect(jsonPath("$.length()").value(2))
                //.andExpect(jsonPath("$.name").value("MacBook"));
        mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(productToCreate)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(expectedProduct)))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.name").value("MacBook"));

    }

}