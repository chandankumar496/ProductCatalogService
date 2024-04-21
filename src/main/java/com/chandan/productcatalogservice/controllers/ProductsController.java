package com.chandan.productcatalogservice.controllers;


import com.chandan.productcatalogservice.dtos.ProductDto;
import com.chandan.productcatalogservice.models.Product;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductsController {


    @GetMapping("{id}")
    public Product getProductById(@PathVariable("id") Long id ){
        Product product = new Product();

        return product;
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto){

        ProductDto productDto1 = new ProductDto();
        return productDto1;
    }
}
