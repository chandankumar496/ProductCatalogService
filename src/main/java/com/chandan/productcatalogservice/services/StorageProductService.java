package com.chandan.productcatalogservice.services;

import com.chandan.productcatalogservice.dtos.UserDto;
import com.chandan.productcatalogservice.models.Product;
import com.chandan.productcatalogservice.repositories.ProductRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

//@Primary
@Service
public class StorageProductService implements IProductService{

    private ProductRepo productRepo;

    private RestTemplate restTemplate;

    public StorageProductService(ProductRepo productRepo, RestTemplate restTemplate) {

        this.productRepo = productRepo;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product getProduct(Long productId) {
         Optional<Product> optionalProduct = productRepo.findProductById(productId);
        return optionalProduct.orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product getProductDetails(Long productId, Long userId) {

      UserDto user = restTemplate.getForEntity("http://userservice/users/{userId}", UserDto.class, userId).getBody();

      if(user != null){
          System.out.println("USER EMAIL: " + user.getEmail());
         Optional<Product> product = productRepo.findProductById(productId);
         return product.orElse(null);
      }
        return null;
    }
}
