package com.falabella.cl.OfflineCodingEvaluation.services.impl;

import com.falabella.cl.OfflineCodingEvaluation.models.Images;
import com.falabella.cl.OfflineCodingEvaluation.models.Product;
import com.falabella.cl.OfflineCodingEvaluation.models.ProductImagesDTO;
import com.falabella.cl.OfflineCodingEvaluation.repositories.IImagesRepository;
import com.falabella.cl.OfflineCodingEvaluation.repositories.IProductRepository;
import com.falabella.cl.OfflineCodingEvaluation.services.IProductDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductDTOService implements IProductDTOService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IImagesRepository imagesRepository;

    @Override
    public List<ProductImagesDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> this.convertEntityToDto(product, imagesRepository.findImagesByProductId(product.getId())))
                .collect(Collectors.toList());

    }

    @Override
    public Optional<ProductImagesDTO> getProductDtoBySku(String sku) {
        return productRepository.findBySku(sku)
                .map(product -> this.convertEntityToDto(product, imagesRepository.findImagesByProductSku(sku)));
    }

    @Override
    public Optional<ProductImagesDTO> getProductDtoById(Long id) {
        return productRepository.findById(id)
                .map(product -> this.convertEntityToDto(product, imagesRepository.findImagesByProductId(id)));
    }

    @Override
    public ProductImagesDTO saveProductDTO(ProductImagesDTO dto) {
        Product _product = new Product();
        _product.setSku(dto.getSku());
        _product.setName(dto.getName());
        _product.setBrand(dto.getBrand());
        _product.setSize(dto.getSize());
        _product.setPrice(dto.getPrice());
        _product.setImageurl(dto.getImageurl());

        _product = productRepository.save(_product);

        Product final_product = _product;
        dto.getImages().forEach(image -> {
            Images imageSave = new Images();

            imageSave.setProduct(final_product);
            imageSave.setUrl(image.getUrl());

            imagesRepository.save(imageSave);
        });


        return dto;
    }

    @Override
    public ProductImagesDTO convertEntityToDto(Product product, Set<Images> images) {
        ProductImagesDTO dto = new ProductImagesDTO();
        dto.setId(product.getId());
        dto.setSku(product.getSku());
        dto.setName(product.getName());
        dto.setBrand(product.getBrand());
        dto.setSize(product.getSize());
        dto.setPrice(product.getPrice());
        dto.setImageurl(product.getImageurl());
        dto.setImages(images);

        return dto;
    }
}
