package com.ismael.ecommerce.service.serviceImpl;

import com.ismael.ecommerce.dto.OrderDTO;
import com.ismael.ecommerce.dto.OrderItemDTO;
import com.ismael.ecommerce.dto.ProductDTO;
import com.ismael.ecommerce.dto.UserDTO;
import com.ismael.ecommerce.model.Order;
import com.ismael.ecommerce.model.OrderItem;
import com.ismael.ecommerce.repository.CartRepository;
import com.ismael.ecommerce.repository.OrderItemRepository;
import com.ismael.ecommerce.repository.OrderRepository;
import com.ismael.ecommerce.repository.UserRepository;
import com.ismael.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public OrderDTO placeOrder(Long userId) {

        var user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));
        var cart = user.getCart();
        if(cart == null || cart.getCartItems().isEmpty()){
            throw new RuntimeException("Cart is empty or not found");
        }

        Order order = new Order();

        order.setUser(user);
        order.setTotalPrice(cart.getTotalPrice());
        order.setStatus("Pending");

        List<OrderItem> orderItems = cart.getCartItems().stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(cartItem.getProduct());
                    orderItem.setOrder(order);
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setPrice(cartItem.getProduct().getPrice());
                    return orderItem;

                }).collect(Collectors.toList());
        order.setItems(orderItems);
        orderRepository.save(order);

        // Clear the cart after order placement
        cart.getCartItems().clear();
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);

        return convertToOrderDTO(order);
    }

    private OrderDTO convertToOrderDTO(Order order) {
        List<OrderItemDTO> orderItemDTOs = order.getItems().stream()
                .map(item -> new OrderItemDTO(
                        item.getId(),
                        new ProductDTO(item.getProduct().getId(), item.getProduct().getName(), item.getProduct().getDescription(), item.getProduct().getPrice(), item.getProduct().getStock()),
                        item.getQuantity(),
                        item.getPrice()
                )).collect(Collectors.toList());

        return new OrderDTO(
                order.getId(),
                new UserDTO(order.getUser().getFirstName(), order.getUser().getLastName(), order.getUser().getEmail()),
                orderItemDTOs,
                order.getTotalPrice(),
                order.getStatus()
        );
    }
}