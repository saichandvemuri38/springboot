package com.example.ProductService.services;

import com.example.ProductService.model.ProductModel;
import com.example.ProductService.model.ProductResponse;

public interface ProductInterface {
    long createProduct(ProductModel product);
    ProductResponse getProductById(long id);

    void reduceQuantity(long id, long quantity);
}
