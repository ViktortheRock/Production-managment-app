package com.example.factory.repositoty;

import com.example.factory.model.ProductivityInHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ProductivityInHourRepository extends JpaRepository<ProductivityInHour, Long> {
//    @Query("""
//            select p from ProductivityInHour p inner join p.product.productivityInHours productivityInHours
//            where p.product.id = ?1 and productivityInHours.date = ?2""")
//    Optional<ProductivityInHour> findByProduct_IdAndProduct_ProductivityInHours_Date(Long id, LocalDateTime date);

    @Query("select p from ProductivityInHour p where p.date = ?1 and p.product.id = ?2")
    Optional<ProductivityInHour> findByDateAndProduct_Id(LocalDateTime date, Long id);
}
