package com.example.factory.service;

import com.example.factory.model.Product;
import com.example.factory.repositoty.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void saveProduct(Product product) {
        productRepository.save(product);
    }
}
