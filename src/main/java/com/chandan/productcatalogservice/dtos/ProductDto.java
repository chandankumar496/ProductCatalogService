package com.chandan.productcatalogservice.dtos;

import com.chandan.productcatalogservice.models.Category;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDto {
    private Long Id;
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private Category category;
}
