package com.chandan.productcatalogservice.services;

import com.chandan.productcatalogservice.clients.FakeStoreApiClient;
import com.chandan.productcatalogservice.dtos.CategoryDto;
import com.chandan.productcatalogservice.dtos.FakeStoreProductDto;
import com.chandan.productcatalogservice.models.Category;
import com.chandan.productcatalogservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//@Primary
@Service
public class ProductService implements IProductService {

    private RestTemplateBuilder restTemplateBuilder;

    private FakeStoreApiClient fakeStoreApiClient;

    public ProductService( RestTemplateBuilder restTemplateBuilder, FakeStoreApiClient fakeStoreApiClient ){

        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreApiClient = fakeStoreApiClient;

    }
    @Override
    public List<Product> getAllProducts() {

        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForEntity("https://fakestoreapi.com/products/", FakeStoreProductDto[].class).getBody();
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(getProduct(fakeStoreProductDto));
        }
        return products;
    }

    @Override
    public Product getProduct(Long productId) {

        FakeStoreProductDto fakeStoreProductDto = fakeStoreApiClient.getProduct(productId);

        return getProduct(fakeStoreProductDto);
    }

    @Override
    public Product createProduct(Product product) {

          RestTemplate restTemplate = restTemplateBuilder.build();
           FakeStoreProductDto fakeStoreProductDtoReq  = getFakeStoreProductDto(product);
         FakeStoreProductDto fakeStoreProductDto = restTemplate.postForEntity("https://fakestoreapi.com/products", fakeStoreProductDtoReq, FakeStoreProductDto.class).getBody();
        return getProduct(fakeStoreProductDto);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
            FakeStoreProductDto fakeStoreProductDtoReq  = getFakeStoreProductDto(product);
          ResponseEntity<FakeStoreProductDto> responseEntity =  putForEntity("https://fakestoreapi.com/products/{id}", fakeStoreProductDtoReq, FakeStoreProductDto.class, id);
        return getProduct(responseEntity.getBody());
    }

    private <T> ResponseEntity<T> putForEntity(String uri, @Nullable Object request, Class<T> responseType, Object... uriVariables) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(uri, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }

    @Override
    public Product getProductDetails(Long productId, Long userId) {





        return null;
    }

    private Product getProduct(FakeStoreProductDto fakeStoreProductDto) {

        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        Category category = new Category();
        category.setCategoryName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    private FakeStoreProductDto getFakeStoreProductDto(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImageUrl());
        if(product.getCategory() != null){
            fakeStoreProductDto.setCategory(product.getCategory().getCategoryName());
        }
      return fakeStoreProductDto;
    }
}
