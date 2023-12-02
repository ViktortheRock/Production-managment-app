package com.example.factory;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ProductivityInHour {

    @Id
    @GeneratedValue
    private Long Id;

    @Temporal(value = TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy/MM/dd HH")
    @Column(nullable = false)
    private Date data;

    @Column(nullable = false)
    private int prodInHour;

    public ProductivityInHour() {}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getProdInHour() {
        return prodInHour;
    }

    public void setProdInHour(int prodInMinute) {
        this.prodInHour = prodInMinute;
    }
}
