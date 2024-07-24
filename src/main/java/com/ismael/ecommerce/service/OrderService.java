package com.ismael.ecommerce.service;

import com.ismael.ecommerce.dto.OrderDTO;

public interface OrderService {
    OrderDTO placeOrder(Long userId);
}
