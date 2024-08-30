package com.chandan.productcatalogservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testAdd_RunsSuccessfully(){

        //Arrange
        Calculator calculator = new Calculator();

        //Act
        int res = calculator.add(2, 3);

        //Assert
        assertEquals(5, res);

    }

    @Test
    void testDivide_ThrowsArithmeticException(){

        // Arrange
        Calculator calculator = new Calculator();

        //Act and Assert
        assertThrows(ArithmeticException.class, () -> {
            calculator.divide(1, 0);
        });

    }

}