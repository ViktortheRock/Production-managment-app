package com.example.factory.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data @NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

   // @Column(nullable = false)
    private Integer numbersInPack;

    @Column
    private String prodLine;

//    @Temporal(value = TemporalType.TIMESTAMP)
////    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss,SSS")
//    @Column(nullable = false)
//    private LocalDateTime dateOfProduction;

    @OneToMany(mappedBy = "product")
    private List<ProductivityInMinute> productivityInMinutes;

//    @PrePersist
//    private void onCreate() {
//        dateOfProduction = LocalDateTime.now();
//    }

}
