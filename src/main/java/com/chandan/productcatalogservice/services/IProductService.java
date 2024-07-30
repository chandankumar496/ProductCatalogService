package com.chandan.productcatalogservice.services;

import com.chandan.productcatalogservice.models.Product;

import java.util.List;

public interface IProductService {

    List<Product> getAllProducts();

    Product getProduct(Long productId);

    Product createProduct(Product product);

    Product replaceProduct(Long id,Product product);

    Product getProductDetails(Long productId, Long userId);

}
