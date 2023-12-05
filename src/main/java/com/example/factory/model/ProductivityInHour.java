package com.example.factory.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Data
@Entity
public class ProductivityInHour {

    @Id
    @GeneratedValue
    private Long Id;

    @Temporal(value = TemporalType.TIMESTAMP)
//    @JsonFormat(pattern = "yyyy/MM/dd HH")
    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false)
    private int prodInHour;

    @OneToMany(mappedBy = "productivityInHour")
    private List<ProductivityInMinute> productivityInMinutes;
}
