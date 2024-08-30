package com.chandan.productcatalogservice.services;

import com.chandan.productcatalogservice.models.Product;
import com.chandan.productcatalogservice.models.SortParam;
import com.chandan.productcatalogservice.models.SortType;
import com.chandan.productcatalogservice.repositories.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private ProductRepo productRepo;

    public SearchService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Page<Product> search(String query, int pageSize, int pageNumber, List<SortParam> sortParams) {

         Sort sort = null;

         if(sortParams != null && !sortParams.isEmpty()) {

             if(sortParams.get(0).getSortType().equals(SortType.ASC)) {
                 sort = Sort.by(sortParams.get(0).getSortName());
             }else{
                 sort = Sort.by(sortParams.get(0).getSortName()).descending();
             }

             for(int i=1; i<sortParams.size();i++) {
                 if(sortParams.get(i).getSortType().equals(SortType.ASC))
                     sort = sort.and(Sort.by(sortParams.get(i).getSortName()));
                 else
                     sort = sort.and(Sort.by(sortParams.get(i).getSortName()).descending());
             }
         }


         return productRepo.findByNameEquals(query, PageRequest.of(pageNumber, pageSize, sort));
    }

}
