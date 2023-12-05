package com.example.factory.service.implement;

import com.example.factory.model.Product;
import com.example.factory.repositoty.ProductRepository;
import com.example.factory.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    @Transactional
//    public void saveProduct(Product product) {
//        productRepository.save(product);
//    }

    @Transactional
    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }
}
