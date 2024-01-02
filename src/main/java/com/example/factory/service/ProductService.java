package com.example.factory.service;

import com.example.factory.dto.ProductResponseDto;
import com.example.factory.model.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product read(long id);
    void delete(long productId);
    List<Product> getAll();
    Product update(Product product);
    List<ProductResponseDto> getAllByMachineId(long machineId);
}
