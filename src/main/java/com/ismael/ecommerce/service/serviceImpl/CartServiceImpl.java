package com.ismael.ecommerce.service.serviceImpl;

import com.ismael.ecommerce.dto.CartDTO;
import com.ismael.ecommerce.dto.CartItemDTO;
import com.ismael.ecommerce.dto.ProductDTO;
import com.ismael.ecommerce.model.Cart;
import com.ismael.ecommerce.model.CartItem;
import com.ismael.ecommerce.model.Product;
import com.ismael.ecommerce.repository.CartItemRepository;
import com.ismael.ecommerce.repository.CartRepository;
import com.ismael.ecommerce.repository.ProductRepository;
import com.ismael.ecommerce.repository.UserRepository;
import com.ismael.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CartDTO addProductToCart(Long userId, Long productId, int quantity) {
        var user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        var product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        var cart = user.getCart();
        if (cart == null) {
            // Create a new cart if none exists
            cart = new Cart();
            cart.setUser(user);
            cart.setTotalPrice(0.0);  // Initialize total price
            user.setCart(cart);
            cartRepository.save(cart);
        }

        Optional<CartItem> existingCartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemRepository.save(cartItem);
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setProduct(product);
            newCartItem.setCart(cart);
            newCartItem.setQuantity(quantity);
            cart.getCartItems().add(newCartItem);
            cartItemRepository.save(newCartItem);
        }

        // Recalculate the total price
        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);

        return convertToCartDTO(cart);
    }

    @Override
    public CartDTO removeProductFromCart(Long userId, Long productId) {
        var user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        var cart = user.getCart();
        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }

        Optional<CartItem> existingCartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cart.getCartItems().remove(cartItem);
            cartItemRepository.delete(cartItem);
        } else {
            throw new RuntimeException("Product not found in cart");
        }

        // Recalculate the total price
        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);

        return convertToCartDTO(cart);
    }

    private CartDTO convertToCartDTO(Cart cart) {
        var cartItemDTOs = cart.getCartItems().stream()
                .map(this::convertToCartItemDTO)
                .collect(Collectors.toList());

        return new CartDTO(cart.getId(), cartItemDTOs, cart.getTotalPrice());
    }

    private CartItemDTO convertToCartItemDTO(CartItem cartItem) {
        ProductDTO productDTO = new ProductDTO(
                cartItem.getProduct().getId(),
                cartItem.getProduct().getName(),
                cartItem.getProduct().getDescription(),
                cartItem.getProduct().getPrice(),
                cartItem.getProduct().getStock()
        );

        return new CartItemDTO(cartItem.getId(), productDTO, cartItem.getQuantity());
    }
}
