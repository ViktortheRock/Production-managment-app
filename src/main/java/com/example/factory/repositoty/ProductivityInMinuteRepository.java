package com.example.factory.repositoty;

import com.example.factory.model.ProductivityInMinute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductivityInMinuteRepository extends JpaRepository<ProductivityInMinute, Long> {

   // public void saveProduct(Product product);
}
