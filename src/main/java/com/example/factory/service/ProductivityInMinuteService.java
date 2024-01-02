package com.example.factory.service;

import com.example.factory.model.Product;
import com.example.factory.model.ProductivityInMinute;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ProductivityInMinuteService {

    ProductivityInMinute create(ProductivityInMinute productivityInMinute);

    int getProductivity(Product product, LocalDateTime time);

    ProductivityInMinute update(ProductivityInMinute productivityInMinute);

    Optional<ProductivityInMinute> findProductivity(ProductivityInMinute productivityInMinute);
}
