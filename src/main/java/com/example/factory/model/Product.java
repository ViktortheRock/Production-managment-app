package com.example.factory.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private ProductName name;

   // @Column(nullable = false)
    private Integer numbersInPack;

    @Column
    private String prodLine;

    @Temporal(value = TemporalType.TIME)
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss,SSS")
//    @Column(nullable = false)
    private Date dateOfProduction;

    public Product() {
        dateOfProduction = new Date();
    }

    public Product(ProductName name) {
        this.dateOfProduction = new Date();
        this.name = name;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public ProductName getName() {return name;}

    public void setName(ProductName name) {this.name = name;}

    public Integer getNumbersInPack() {return numbersInPack;}

    public void setNumbersInPack(Integer numbersInPack) {this.numbersInPack = numbersInPack;}

    public String getProdLine() {return prodLine;}

    public void setProdLine(String prodLine) {this.prodLine = prodLine;}

    public Date getDateOfProduction() {return dateOfProduction;}

    public void setDateOfProduction(Date prodDate) {this.dateOfProduction = prodDate;}

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name.name() + '\'' +
                ", numbersInPack=" + numbersInPack +
                ", prodLine='" + prodLine + '\'' +
                ", dateOfProduction=" + dateOfProduction +
                '}';
    }
}
