package com.business.product.services;

import com.business.product.entity.Product;
import com.business.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository prodRepo;

    @Override
    public Product getProductById(int id) {
        Optional<Product> products = prodRepo.findById(id);
        Product product;
        if(products.isPresent()){
            product = products.get();
        }else {
            throw new RuntimeException("Product that have ID, " + id + "has not found");
        }
        return product;
    }

    @Override
    public Product saveProduct(Product product) {
        prodRepo.save(product);
        return product;
    }

    @Override
    public List<Product> getAllProduct() {
        return prodRepo.findAll();
    }
}
