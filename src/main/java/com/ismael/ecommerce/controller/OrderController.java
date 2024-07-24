package com.ismael.ecommerce.controller;

import com.ismael.ecommerce.dto.OrderDTO;
import com.ismael.ecommerce.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Endpoint to create a new order
    @PostMapping("/place/{userId}")
    public ResponseEntity<OrderDTO> placeOrder(
            @PathVariable Long userId) {
        try {
            OrderDTO orderDTO = orderService.placeOrder(userId);
            return ResponseEntity.ok(orderDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
