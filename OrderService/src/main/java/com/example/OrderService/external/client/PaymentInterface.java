package com.example.OrderService.external.client;

import com.example.OrderService.exceptions.CustomException;
import com.example.OrderService.external.request.PaymentModel;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CircuitBreaker(name = "external", fallbackMethod = "fallbackMethod")
@FeignClient(name = "PAYMENT-SERVICE/payment")
public interface PaymentInterface {
    @PostMapping
    ResponseEntity<Long> doPayment(@RequestBody PaymentModel paymentModel);

    default void fallbackMethod(Exception e){
        throw new CustomException("Payment Failed","UNAVAILABLE", 500);
    }
}
