package com.ismael.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long orderId;
    private UserDTO user;
    private List<OrderItemDTO> orderItems = new ArrayList<>();
    private Double totalAmount;
    private String orderStatus;


}
