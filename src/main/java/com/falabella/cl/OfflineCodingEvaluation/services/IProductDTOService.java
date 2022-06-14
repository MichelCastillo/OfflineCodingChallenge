package com.falabella.cl.OfflineCodingEvaluation.services;

import com.falabella.cl.OfflineCodingEvaluation.models.Images;
import com.falabella.cl.OfflineCodingEvaluation.models.Product;
import com.falabella.cl.OfflineCodingEvaluation.models.ProductImagesDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IProductDTOService {

    List<ProductImagesDTO> getAllProducts();

    Optional<ProductImagesDTO> getProductDtoBySku(String SKU);

    Optional<ProductImagesDTO> getProductDtoById(Long id);

    ProductImagesDTO saveProductDTO(ProductImagesDTO dto);

    ProductImagesDTO convertEntityToDto(Product product, Set<Images> images);

}
