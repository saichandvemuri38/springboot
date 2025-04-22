package com.example.Payment.controller;

import com.example.Payment.model.PaymentModel;
import com.example.Payment.model.PaymentResponse;
import com.example.Payment.service.PaymentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentInterface paymentInterface;

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentModel paymentModel) {
        return new ResponseEntity<>(paymentInterface.doPayment(paymentModel), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable("id") long id) {
        return new ResponseEntity<>(paymentInterface.getPayment(id), HttpStatus.OK);
    }


}
