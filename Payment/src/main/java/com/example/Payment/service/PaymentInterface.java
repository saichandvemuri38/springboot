package com.example.Payment.service;

import com.example.Payment.model.PaymentModel;
import com.example.Payment.model.PaymentResponse;

public interface PaymentInterface {
    long doPayment(PaymentModel paymentModel);

    PaymentResponse getPayment(long id);
}
