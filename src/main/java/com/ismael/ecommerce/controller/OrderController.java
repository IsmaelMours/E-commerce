package com.ismael.ecommerce.controller;

import com.ismael.ecommerce.dto.OrderDTO;
import com.ismael.ecommerce.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order Management", description = "Operations related to order management in the application")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place/{userId}")
    @Operation(summary = "Place a new order", description = "Creates a new order for the specified user ID.")
    public ResponseEntity<OrderDTO> placeOrder(@PathVariable Long userId) {
        OrderDTO orderDTO = orderService.placeOrder(userId);
        return ResponseEntity.ok(orderDTO);
    }

    @DeleteMapping("/cancel/{orderId}")
    @Operation(summary = "Cancel an order", description = "Cancels an existing order by its unique ID.")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{orderId}/{status}")
    @Operation(summary = "Update order status", description = "Updates the status of an existing order by its unique ID.")
    public ResponseEntity<OrderDTO> updateOrderStatus(
            @PathVariable Long orderId,
            @PathVariable String status) {
        OrderDTO orderDTO = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(orderDTO);
    }

    @GetMapping("/history/{userId}")
    @Operation(summary = "Get order history", description = "Fetches the order history for a specific user ID.")
    public ResponseEntity<List<OrderDTO>> getOrderHistory(@PathVariable Long userId) {
        List<OrderDTO> orders = orderService.getOrderHistory(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "Get order details", description = "Fetches the details of an order by its unique ID.")
    public ResponseEntity<OrderDTO> getOrderDetails(@PathVariable Long orderId) {
        OrderDTO orderDTO = orderService.getOrderDetails(orderId);
        return ResponseEntity.ok(orderDTO);
    }
}
