package com.example.factory.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data @Builder
@Entity
public class ProductivityInMinute {

    @Id
    @GeneratedValue
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Temporal(value = TemporalType.TIMESTAMP)
//    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false)
    private int prodInMinute;

    @ManyToOne
    @JoinColumn(name = "productivityInHour_id")
    private ProductivityInHour productivityInHour;
}
