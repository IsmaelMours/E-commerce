package com.ismael.ecommerce.controller;

import com.ismael.ecommerce.dto.CartDTO;
import com.ismael.ecommerce.service.CartService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add/{userId}/{productId}/{quantity}")
    public ResponseEntity<CartDTO> addProductToCart(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @PathVariable int quantity) {

            CartDTO cartDTO = cartService.addProductToCart(userId, productId, quantity);
            return ResponseEntity.ok(cartDTO);

    }


    @DeleteMapping("/remove/{userId}/{productId}")
    public ResponseEntity<CartDTO> removeProductFromCart(
            @PathVariable Long userId,
            @PathVariable Long productId) {


            CartDTO cartDTO = cartService.removeProductFromCart(userId, productId);
            return ResponseEntity.ok(cartDTO);

    }
}

