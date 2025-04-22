package com.example.OrderService.services;

import com.example.OrderService.entity.OrderEntity;
import com.example.OrderService.exceptions.CustomException;
import com.example.OrderService.external.client.PaymentInterface;
import com.example.OrderService.external.client.ProductInterface;
import com.example.OrderService.external.request.PaymentModel;
import com.example.OrderService.models.OrderRequest;
import com.example.OrderService.models.OrderResponse;
import com.example.OrderService.models.PaymentResponse;
import com.example.OrderService.models.ProductResponse;
import com.example.OrderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.Instant;

@Log4j2
@Service
public class OrderService implements OrderInterface {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductInterface productInterface;
    @Autowired
    private PaymentInterface paymentInterface;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public long placeOrder(OrderRequest orderRequest) {
        log.info("calling product service:{}", orderRequest);
        productInterface.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
        log.info("placing order service:{}", orderRequest);
        OrderEntity orderEntity = OrderEntity.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();
        orderEntity = orderRepository.save(orderEntity);
        log.info("payment order service:{}", orderEntity);
        PaymentModel paymentModel = PaymentModel.builder()
                .orderId(orderEntity.getId())
                .amount(orderEntity.getAmount())
                .paymentMode(orderRequest.getPaymentMode())
                .build();
        String orderStatus = null;
        try {
            paymentInterface.doPayment(paymentModel);
            orderStatus = "SUCCESS";
        } catch (Exception e) {
            orderStatus = "FAILED";
        }
        orderEntity.setOrderStatus(orderStatus);
        orderRepository.save(orderEntity);

        return orderEntity.getId();
    }

    @Override
    public OrderResponse getOrderByID(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new CustomException("Order not found", "ORDER_NOT_FOUND", 404));
        ProductResponse productResponse = restTemplate.getForObject("http://PRODUCT-SERVICE/products/" + orderEntity.getProductId(), ProductResponse.class);
        OrderResponse.ProductDetails productDetails = OrderResponse.ProductDetails.builder()
                .productName(productResponse.getName())
                .productId(productResponse.getId())
                .quantity(orderEntity.getQuantity())
                .build();
        PaymentResponse paymentResponse = restTemplate.getForObject("http://PAYMENT-SERVICE/payment/" + orderEntity.getId(), PaymentResponse.class);
        OrderResponse.PaymentDetails paymentDetails = OrderResponse.PaymentDetails.builder()
                .paymentId(paymentResponse.getPaymentId())
                .paymentMode(paymentResponse.getPaymentMode())
                .amount(paymentResponse.getAmount())
                .paymentDate(paymentResponse.getPaymentDate())
                .status(paymentResponse.getStatus())
                .build();
        OrderResponse orderResponse = OrderResponse.builder()
                .orderId(orderEntity.getId())
                .amount(orderEntity.getAmount())
                .orderStatus(orderEntity.getOrderStatus())
                .orderDate(orderEntity.getOrderDate())
                .productDetails(productDetails)
                .paymentDetails(paymentDetails)
                .build();
        return orderResponse;
    }
}
