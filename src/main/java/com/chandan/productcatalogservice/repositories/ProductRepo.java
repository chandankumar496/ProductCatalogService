package com.chandan.productcatalogservice.repositories;

import com.chandan.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {


     Product save(Product product);

     Optional<Product> findProductById(Long id);




}
