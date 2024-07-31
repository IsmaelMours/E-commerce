package com.ismael.ecommerce.controller;

import com.ismael.ecommerce.dto.UserDTO;
import com.ismael.ecommerce.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User Management", description = "Operations pertaining to users in the application")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    @Operation(summary = "Create a new user", description = "This endpoint allows you to create a new user in the system.")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.addUser(userDTO));
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Get user by ID", description = "Fetches a user by their unique ID.")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        var user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete user by ID", description = "Deletes a user from the system by their unique ID.")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update user", description = "Updates an existing user in the system by their unique ID.")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO){
        var updateUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updateUser);
    }
}
