package com.example.Payment.service;

import com.example.Payment.entity.PaymentEntity;
import com.example.Payment.model.PaymentMode;
import com.example.Payment.model.PaymentModel;
import com.example.Payment.model.PaymentResponse;
import com.example.Payment.repository.PaymentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentService implements PaymentInterface {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public long doPayment(PaymentModel paymentModel) {
        log.info("Recorded payment",paymentModel);
        PaymentEntity paymentEntity = PaymentEntity.builder()
                .amount(paymentModel.getAmount())
                .paymentDate(Instant.now())
                .paymentMode(paymentModel.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .orderId(paymentModel.getOrderId())
                .referenceNumber(paymentModel.getReferenceNumber())
                .amount(paymentModel.getAmount())
                .build();
        paymentRepository.save(paymentEntity);
        log.info("Payment Recorded: {}", paymentEntity.getId());
        return paymentEntity.getId();
    }

    @Override
    public PaymentResponse getPayment(long id) {
        PaymentEntity paymentEntity = paymentRepository.findByOrderId(Long.valueOf(id));
        log.info(paymentEntity);
        PaymentResponse response = PaymentResponse.builder()
                .paymentId(paymentEntity.getId())
                .amount(paymentEntity.getAmount())
                .status(paymentEntity.getPaymentStatus())
                .orderId(paymentEntity.getOrderId())
                .paymentDate(paymentEntity.getPaymentDate())
                .paymentMode(PaymentMode.valueOf(paymentEntity.getPaymentMode()))
                .build();
        return response;
    }
}
