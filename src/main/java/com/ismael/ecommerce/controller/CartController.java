package com.ismael.ecommerce.controller;

import com.ismael.ecommerce.dto.CartDTO;
import com.ismael.ecommerce.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@Tag(name = "Cart Management", description = "Operations related to cart management in the application")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add/{userId}/{productId}/{quantity}")
    @Operation(summary = "Add product to cart", description = "Adds a specified product to the cart with the given quantity for the user.")
    public ResponseEntity<CartDTO> addProductToCart(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @PathVariable int quantity) {
        CartDTO cartDTO = cartService.addProductToCart(userId, productId, quantity);
        return ResponseEntity.ok(cartDTO);
    }

    @DeleteMapping("/remove/{userId}/{productId}")
    @Operation(summary = "Remove product from cart", description = "Removes a specified product from the cart for the user.")
    public ResponseEntity<CartDTO> removeProductFromCart(
            @PathVariable Long userId,
            @PathVariable Long productId) {
        CartDTO cartDTO = cartService.removeProductFromCart(userId, productId);
        return ResponseEntity.ok(cartDTO);
    }
}
