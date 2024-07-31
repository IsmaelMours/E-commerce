package com.ismael.ecommerce.controller;

import com.ismael.ecommerce.dto.ProductDTO;
import com.ismael.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@Tag(name = "Product Management", description = "Operations related to product management in the application")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    @Operation(summary = "Create a new product", description = "This endpoint allows you to create a new product in the system.")
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO){
        var addProduct = productService.addProduct(productDTO);
        return ResponseEntity.ok(addProduct);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Fetches a product by its unique ID.")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id){
        var product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product by ID", description = "Deletes a product from the system by its unique ID.")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update product", description = "Updates an existing product in the system by its unique ID.")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO){
        var updateProduct = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updateProduct);
    }
}
