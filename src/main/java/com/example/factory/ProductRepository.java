package com.example.factory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ProductRepository extends JpaRepository <Product, Long> {

//    Product findByDateOfProduction(Date date);
}
