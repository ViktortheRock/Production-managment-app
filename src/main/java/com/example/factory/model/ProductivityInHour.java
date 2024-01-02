package com.example.factory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Data @Builder
@Entity
public class ProductivityInHour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Temporal(value = TemporalType.TIMESTAMP)
//    @JsonFormat(pattern = "yyyy/MM/dd HH")
    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private int prodInHour;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "productivityInHour")
    private List<ProductivityInMinute> productivityInMinutes;
}
