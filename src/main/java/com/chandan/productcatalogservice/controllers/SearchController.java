package com.chandan.productcatalogservice.controllers;

import com.chandan.productcatalogservice.dtos.SearchRequestDto;
import com.chandan.productcatalogservice.models.Product;
import com.chandan.productcatalogservice.services.SearchService;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public Page<Product> searchProducts(@RequestBody SearchRequestDto searchRequestDto){


        return searchService.search(searchRequestDto.getQuery(), searchRequestDto.getPageSize(), searchRequestDto.getPageNumber(), searchRequestDto.getSortParamList());
    }


}
