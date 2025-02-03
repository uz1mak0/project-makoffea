package com.business.product.services;

import com.business.product.entity.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(int id);
    Product saveProduct(Product product);
    List<Product>getAllProduct();
}
