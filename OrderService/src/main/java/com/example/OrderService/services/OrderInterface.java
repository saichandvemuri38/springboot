package com.example.OrderService.services;

import com.example.OrderService.entity.OrderEntity;
import com.example.OrderService.models.OrderRequest;
import com.example.OrderService.models.OrderResponse;

public interface OrderInterface {

    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderByID(Long orderId);
}
