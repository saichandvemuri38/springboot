package com.example.ProductService.services;

import com.example.ProductService.entity.ProductEntity;
import com.example.ProductService.exceptions.ProductServiceCustomExceptions;
import com.example.ProductService.model.ProductModel;
import com.example.ProductService.model.ProductResponse;
import com.example.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@Log4j2
public class ProductService implements ProductInterface {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public long createProduct(ProductModel product) {
        log.info("Creating product");
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        productEntity.setQuantity(product.getQuantity());
        productRepository.save(productEntity);
        return productEntity.getId();
    }

    @Override
    public ProductResponse getProductById(long id) {
        log.info("Getting product by id");
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new ProductServiceCustomExceptions("Product not found","PRODUCT_NOT_FOUND"));
        ProductResponse productModel = new ProductResponse();
        copyProperties(productEntity, productModel);
        return productModel;
    }

    @Override
    public void reduceQuantity(long id, long quantity) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new ProductServiceCustomExceptions("Product with id not found","PRODUCT_NOT_FOUND"));
        log.info("Quantity",productEntity.getQuantity());
        if(productEntity.getQuantity() < quantity){
            throw new ProductServiceCustomExceptions("Quantity is not available","QUANTITY_NOT_AVAILABLE");
        }
        productEntity.setQuantity(productEntity.getQuantity() - quantity);
        productRepository.save(productEntity);

    }

}
