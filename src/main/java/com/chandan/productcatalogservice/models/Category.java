package com.chandan.productcatalogservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category extends BaseModel{

    private String categoryName;
    private String categoryDescription;
    @JsonBackReference
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
