package com.falabella.cl.OfflineCodingEvaluation.models;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Set;

@Data
public class ProductImagesDTO {

    private Long id;

    private String sku;

    private String name;

    private String brand;

    private String size;

    private BigDecimal price;

    private String imageurl;

    private Set<Images> images;

}
