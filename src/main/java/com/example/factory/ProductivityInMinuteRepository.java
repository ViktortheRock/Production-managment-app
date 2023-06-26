package com.example.factory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductivityInMinuteRepository extends JpaRepository<ProductivityInMinute, Long> {

   // public void saveProduct(Product product);
}
