package com.ismael.ecommerce.controller;

import com.ismael.ecommerce.dto.OrderDTO;
import com.ismael.ecommerce.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Endpoint to create a new order
    @PostMapping("/place/{userId}")
    public ResponseEntity<OrderDTO> placeOrder(
            @PathVariable Long userId) {

            OrderDTO orderDTO = orderService.placeOrder(userId);
            return ResponseEntity.ok(orderDTO);

    }

    @DeleteMapping("/cancel/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {

            orderService.cancelOrder(orderId);
            return ResponseEntity.noContent().build();

    }

    @PutMapping("/update/{orderId}/{status}")
    public ResponseEntity<OrderDTO> updateOrderStatus(
            @PathVariable Long orderId,
            @PathVariable String status) {
            OrderDTO orderDTO = orderService.updateOrderStatus(orderId, status);
            return ResponseEntity.ok(orderDTO);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<OrderDTO>> getOrderHistory(@PathVariable Long userId) {
            List<OrderDTO> orders = orderService.getOrderHistory(userId);
            return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderDetails(@PathVariable Long orderId) {
            OrderDTO orderDTO = orderService.getOrderDetails(orderId);
            return ResponseEntity.ok(orderDTO);
    }



}
