package com.falabella.cl.OfflineCodingEvaluation.repositories;

import com.falabella.cl.OfflineCodingEvaluation.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    @Query("select p from Product p where p.sku=?1")
    Optional<Product> findByKPU(String sku);

    void delete(Product product);

}
