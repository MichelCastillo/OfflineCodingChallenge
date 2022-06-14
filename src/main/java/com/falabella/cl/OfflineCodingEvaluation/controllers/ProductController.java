package com.falabella.cl.OfflineCodingEvaluation.controllers;

import com.falabella.cl.OfflineCodingEvaluation.exceptions.ProductNotFoundException;
import com.falabella.cl.OfflineCodingEvaluation.models.Images;
import com.falabella.cl.OfflineCodingEvaluation.models.Product;
import com.falabella.cl.OfflineCodingEvaluation.services.IImagesService;
import com.falabella.cl.OfflineCodingEvaluation.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IImagesService imagesService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable(name = "id") long id){

        Optional<Product> product = productService.findProductById(id);

        if (product.isPresent()){
            Map<String, Object> response = new HashMap();
            Set<Images> productImages = imagesService.findImagesByProductId(id);

            response.put("product", product.get());
            response.put("images", productImages);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/sku/{SKU}")
    public ResponseEntity<?> getProduct(@PathVariable(name = "SKU") String sku){

        Optional<Product> product = productService.findProductBySku(sku);

        if (product.isPresent()){
            Map<String, Object> response = new HashMap();
            Set<Images> productImages = imagesService.findImagesByProductSku(sku);

            response.put("product", product.get());
            response.put("images", productImages);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/saveProduct")
    public ResponseEntity<?> postProduct(@RequestBody Product product){

        if (productService.save(new Product(
                product.getSku(),
                product.getName(),
                product.getBrand(),
                product.getSize(),
                product.getPrice(),
                product.getImageurl()
        )) != null){
            return ResponseEntity.accepted().body("Product saved successfully.");
        }

        return ResponseEntity.unprocessableEntity().body("There was an error saving the product.");
    }

    @PutMapping("/modifyProduct/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") long id, @RequestBody Product product){

        Product _product = productService.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("There is no Product with that ID"));

        _product.setSku(product.getSku());
        _product.setName(product.getName());
        _product.setBrand(product.getBrand());
        _product.setPrice(product.getPrice());
        _product.setImageurl(product.getImageurl());

        return new ResponseEntity<>(productService.save(_product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity<Object> deleteProduct(@PathVariable(name = "id") long id){
        productService.deleteProductById(id);

        return new ResponseEntity<>(NO_CONTENT);
    }

}
