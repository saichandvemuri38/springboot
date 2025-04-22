package com.example.ProductService.exceptions;

import lombok.Data;

@Data
public class ProductServiceCustomExceptions extends RuntimeException{
    private String errorCode;
    public ProductServiceCustomExceptions(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
