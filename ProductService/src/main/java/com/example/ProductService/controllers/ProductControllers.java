package com.example.ProductService.controllers;

import com.example.ProductService.model.ProductModel;
import com.example.ProductService.model.ProductResponse;
import com.example.ProductService.services.ProductInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductControllers {
    @Autowired
    private ProductInterface productInterface;

    @PostMapping
    public ResponseEntity<Long> createProduct(@RequestBody ProductModel product) {
        long productid = this.productInterface.createProduct(product);
        return ResponseEntity.ok(productid);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductsById(@PathVariable("id") long id) {
        ProductResponse products = productInterface.getProductById(id);
        return new ResponseEntity<>(products, null, HttpStatus.OK);
    }
    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long id, @RequestParam long quantity) {
        productInterface.reduceQuantity(id, quantity);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
