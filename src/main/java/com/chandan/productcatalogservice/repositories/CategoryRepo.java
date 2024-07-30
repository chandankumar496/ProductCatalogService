package com.chandan.productcatalogservice.repositories;

import com.chandan.productcatalogservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {


    Optional<Category> findAllById(Long id);
}
