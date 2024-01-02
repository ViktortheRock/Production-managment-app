package com.example.factory.repositoty;

import com.example.factory.model.ProductivityInMinute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ProductivityInMinuteRepository extends JpaRepository<ProductivityInMinute, Long> {
    @Query("select p from ProductivityInMinute p where p.date = ?1 and p.product.id = ?2")
    Optional<ProductivityInMinute> findByDateAndProduct_Id(LocalDateTime date, Long id);
//    @Transactional
//    @Modifying
//    @Query("update ProductivityInMinute p set p.prodInMinute = ?1 where p.id = ?2")
//    int updateProdInMinuteById(int prodInMinute, Long Id);
//    Optional<ProductivityInMinute> findByProduct_IdAndProduct_ProductivityInMinutes_Date(Long id, LocalDateTime date);

}
