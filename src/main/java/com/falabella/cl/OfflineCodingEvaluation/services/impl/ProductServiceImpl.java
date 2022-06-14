package com.falabella.cl.OfflineCodingEvaluation.services.impl;

import com.falabella.cl.OfflineCodingEvaluation.models.Product;
import com.falabella.cl.OfflineCodingEvaluation.repositories.IProductRepository;
import com.falabella.cl.OfflineCodingEvaluation.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    @Transactional
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAllProducts() {
        return null;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> findProductBySku(String sku) {
        return productRepository.findBySku(sku);
    }
}
