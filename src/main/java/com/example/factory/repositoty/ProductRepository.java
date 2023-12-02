package com.example.factory.repositoty;

import com.example.factory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Long> {

//    Product findByDateOfProduction(Date date);
}
