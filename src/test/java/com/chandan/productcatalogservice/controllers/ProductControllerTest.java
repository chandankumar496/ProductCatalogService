package com.chandan.productcatalogservice.controllers;

import com.chandan.productcatalogservice.models.Product;
import com.chandan.productcatalogservice.services.IProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductsController productsController;

    @MockBean
    private IProductService productService;

    @Captor
    private ArgumentCaptor<Long> idCapter;

    @Test
    @DisplayName("product fetched successfully  - happy path")
    public void Test_Get_Product_WithValidId_ReturnsProductSuccessfully(){

        //Arrange
        Product product = new Product();
        product.setName("Iphone");
        product.setPrice(1000D);
        when(productService.getProduct(any(Long.class))).thenReturn(product);

        //Act
         ResponseEntity<Product> responseEntity =  productsController.getProductById(1L);

         //Assert

        assertNotNull(responseEntity.getBody());
        assertEquals(1000D, responseEntity.getBody().getPrice());
        assertEquals("Iphone", responseEntity.getBody().getName());

    }

    @Test
    @DisplayName("run time exception from mocked dependency  - sad path")
    public void Test_Get_Product_ExternalDependencyThrowsException(){

        //Arrange
        when(productService.getProduct(any(Long.class))).thenThrow(new RuntimeException());

        //Assert
        assertThrows(RuntimeException.class, ()->productsController.getProductById(1L));

    }

    @Test
    @DisplayName("illegal argument exception when 0 product id was passed  - sad path")
    public void Test_Get_Product_WithInvalidId_ThrowsIllegalArgumentException(){
        //Assert
        assertThrows(IllegalArgumentException.class, ()->productsController.getProductById(0L));

        verify(productService, times(0)).getProduct(0L);
    }

    @Test
    public void Test_ProductServiceCalledWithExpectedArguments_Successfully(){

        //Arrange
        Long id = 2L;

       //Act
       productsController.getProductById(id);

        //Assert
        verify(productService).getProduct(idCapter.capture());
        assertEquals(id, idCapter.getValue());

    }

}