package com.chandan.productcatalogservice.controllers;


import com.chandan.productcatalogservice.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsController {


    @GetMapping("{id}")
    public Product getProductById(@PathVariable("id") Long id ){
        Product product = new Product();
        return product;
    }
}
