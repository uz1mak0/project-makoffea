package com.business.product.controller;

import com.business.product.entity.Product;
import com.business.product.repository.ProductRepository;
import com.business.product.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductControllerTest {

    @Mock
    private ProductRepository productRepo;

    @InjectMocks
        private ProductService productService;


    @Test
    void getProductById() {
    }

    @Test
    void newProduct() {
        Product productDetails = new Product();

        productDetails.setName("Caramel Bread");
        productDetails.setFlavour("Strawberry");
        productDetails.setPrice(5);


        int productId = 1;

        Optional<Product> actualProd = Optional.of(productRepo.getById(productId));

        assertTrue(actualProd.isEmpty());
        assertEquals(productDetails.getName(), actualProd.get().getName());
        assertEquals(productDetails.getFlavour(), actualProd.get().getFlavour());
        assertEquals(productDetails.getPrice(), actualProd.get().getPrice());
    }

    @Test
    void getAllProducts() {
    }
}