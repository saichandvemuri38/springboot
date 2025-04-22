package com.example.OrderService.external.client;


import com.example.OrderService.exceptions.CustomException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CircuitBreaker(name = "external", fallbackMethod = "fallbackMethod")
@FeignClient(name = "PRODUCT-SERVICE/products")
public interface ProductInterface {
    @PutMapping("/reduceQuantity/{id}")
    ResponseEntity<Void> reduceQuantity(@PathVariable("id") long id, @RequestParam long quantity);

    default void fallbackMethod(Exception e){
        throw new CustomException("Product Failed","UNAVAILABLE", 500);
    }
}
