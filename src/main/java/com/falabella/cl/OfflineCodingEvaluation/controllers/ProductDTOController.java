package com.falabella.cl.OfflineCodingEvaluation.controllers;

import com.falabella.cl.OfflineCodingEvaluation.models.Images;
import com.falabella.cl.OfflineCodingEvaluation.models.Product;
import com.falabella.cl.OfflineCodingEvaluation.models.ProductImagesDTO;
import com.falabella.cl.OfflineCodingEvaluation.services.IImagesService;
import com.falabella.cl.OfflineCodingEvaluation.services.IProductDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductDTOController {

    @Autowired
    private IProductDTOService productService;

    @GetMapping(value = {"/all", "/product/{sku}"})
    public ResponseEntity<List<ProductImagesDTO>> getProducts(@PathVariable Optional<String> sku){

        try {
            List<ProductImagesDTO> products = sku.map(s -> productService
                                                            .getAllProducts()
                                                            .stream()
                                                            .filter(product -> product.getSku().equals(s))
                                                            .collect(Collectors.toList()))
                                                .orElseGet(() -> productService.getAllProducts());

            if (!products.isEmpty()){
                return new ResponseEntity<>(products, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/saveProduct")
    public ResponseEntity<?> postProduct(@RequestBody ProductImagesDTO dto){

        if (productService.saveProductDTO(dto) != null){
            return ResponseEntity.accepted().body("Product saved successfully.");
        }

        return ResponseEntity.unprocessableEntity().body("There was an error saving the product.");
    }

}
