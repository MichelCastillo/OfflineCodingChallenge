package com.falabella.cl.OfflineCodingEvaluation.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "sku")
    private String sku;

    private String name;

    private String brand;

    private String size;

    private BigDecimal price;

    private String imageurl;

    public Product(String sku, String name, String brand, String size, BigDecimal price, String imageurl) {
        this.sku = sku;
        this.name = name;
        this.brand = brand;
        this.size = size;
        this.price = price;
        this.imageurl = imageurl;
    }
}
