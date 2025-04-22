package com.example.OrderService.external.request;

import com.example.OrderService.models.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentModel {
    private long orderId;
    private String referenceNumber;
    private long amount;
    private PaymentMode paymentMode;
}
