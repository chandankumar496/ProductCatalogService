package com.chandan.productcatalogservice.controllers;


import com.chandan.productcatalogservice.dtos.ProductDto;
import com.chandan.productcatalogservice.models.Category;
import com.chandan.productcatalogservice.models.Product;
import com.chandan.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    IProductService productService;

    @GetMapping("{productId}/{userId}")
    public ProductDto getProductBasedOnUserScope(@PathVariable Long productId, @PathVariable Long userId) {

        Product product = productService.getProductDetails(productId, userId);
        return getProductDTO(product);
    }

    @GetMapping
    public List<Product> getAllProducts(){
         return productService.getAllProducts();
     }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long productId ){

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        try {
            if(productId < 1){
                headers.add("called by", "bad people");
                throw new IllegalArgumentException("id is invalid");
            }

            Product product = productService.getProduct(productId);
            headers.add("called by", "smart people");
            return new ResponseEntity<>(product, headers, HttpStatus.OK);
        }catch (Exception e){
           return new ResponseEntity<>(headers,HttpStatus.BAD_REQUEST);
            //throw e;
        }

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto){
          Product product = productService.createProduct(getProduct(productDto));
          if(Objects.isNull(product)){
              return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
          }
          return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto){

        Product product = productService.replaceProduct(id, getProduct(productDto));
        if(Objects.isNull(product)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    private Product getProduct(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
        Category category = new Category();
        if (productDto.getCategory() != null){
            category.setCategoryDescription(productDto.getCategory().getCategoryDescription());
            category.setCategoryName(productDto.getCategory().getCategoryName());
        }
        product.setCategory(category);
        return product;
    }

    private ProductDto getProductDTO(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setPrice(product.getPrice());
        Category category = product.getCategory();
        productDto.setCategory(category);
        return productDto;
    }
}
