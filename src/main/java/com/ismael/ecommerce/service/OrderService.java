package com.ismael.ecommerce.service;

import com.ismael.ecommerce.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO placeOrder(Long userId);
    void cancelOrder(Long orderId);
    OrderDTO updateOrderStatus(Long orderId, String status);
    List<OrderDTO> getOrderHistory(Long userId);
    OrderDTO getOrderDetails(Long orderId);
}
