package com.ismael.ecommerce.service;

import com.ismael.ecommerce.dto.CartDTO;

public interface CartService {
    CartDTO addProductToCart(Long userId, Long productId, int quantity);
    CartDTO removeProductFromCart(Long userId, Long productId);
}
