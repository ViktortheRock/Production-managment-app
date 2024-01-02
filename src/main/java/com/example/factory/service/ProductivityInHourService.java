package com.example.factory.service;

import com.example.factory.model.Product;
import com.example.factory.model.ProductivityInHour;
import com.example.factory.model.ProductivityInMinute;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ProductivityInHourService {

    ProductivityInHour create(ProductivityInHour productivityInHour);

    int getProductivity(Product product, LocalDateTime currentTime);

    ProductivityInHour update(ProductivityInHour productivityInHour);

    Optional<ProductivityInHour> findProductivity(ProductivityInHour productivityInHour);
}
