package com.example.OrderService.controllers;

import com.example.OrderService.entity.OrderEntity;
import com.example.OrderService.models.OrderRequest;
import com.example.OrderService.models.OrderResponse;
import com.example.OrderService.services.OrderInterface;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderControllers {
    @Autowired
    private OrderInterface orderInterface;

    @PostMapping("/placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest){
        long orderId = orderInterface.placeOrder(orderRequest);
        log.info(orderId);
       return new ResponseEntity<>(orderId,HttpStatus.OK);

    }
    @GetMapping("/getOrderById/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable("id") Long orderId){
        return new ResponseEntity<>(orderInterface.getOrderByID(orderId),HttpStatus.OK);
    }

}
