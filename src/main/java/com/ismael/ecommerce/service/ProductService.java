package com.ismael.ecommerce.service;

import com.ismael.ecommerce.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO addProduct(ProductDTO productDTO);
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    void deleteProductById(Long id);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
}
