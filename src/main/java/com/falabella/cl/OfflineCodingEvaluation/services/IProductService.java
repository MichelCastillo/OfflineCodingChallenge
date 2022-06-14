package com.falabella.cl.OfflineCodingEvaluation.services;

import com.falabella.cl.OfflineCodingEvaluation.models.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    Optional<Product> findProductById(Long id);

    List<Product> findAllProducts();

    Product save(Product product);

    void updateProduct(Product product);

    void deleteProductById(long id);

    Optional<Product> findProductBySku(String sku);
}
