package com.ismael.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    @NotBlank(message = "product name is mandatory")
    private String name;
    @NotBlank(message = "product description is mandatory")
    private String description;
    @NotBlank(message = "product price is mandatory")
    private double price;
    @NotBlank(message = "product stock is mandatory")
    private int stock;


}
