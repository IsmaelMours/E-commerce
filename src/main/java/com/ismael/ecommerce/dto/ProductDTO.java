package com.ismael.ecommerce.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;


}
