package com.business.product.controller;

import com.business.product.entity.Product;
import com.business.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/makoffea/{productId}")
    public ResponseEntity<Product> getProductById (@PathVariable (value = "id") int id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping("/makoffea/new")
    public ResponseEntity<Product> newProduct(@RequestBody Product product) {
        Product newProd = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProd);
    }

    @GetMapping("/makoffea/allproducts")
    public ResponseEntity<Product> getAllProducts() {
        List<Product> allProducts = productService.getAllProduct();
        return ResponseEntity.status(HttpStatus.OK).body((Product) allProducts);
    }
}
