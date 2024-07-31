package com.ismael.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "first name  is mandatory")
    private String firstName;

    @NotBlank(message = "first name  is mandatory")
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    private String email;


}
