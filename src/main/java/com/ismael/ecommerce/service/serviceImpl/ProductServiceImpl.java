package com.ismael.ecommerce.service.serviceImpl;

import com.ismael.ecommerce.dto.ProductDTO;
import com.ismael.ecommerce.model.Product;
import com.ismael.ecommerce.repository.ProductRepository;
import com.ismael.ecommerce.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        var product = new Product();
        product.setName(productDTO.getName());
        product.setStock(productDTO.getStock());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        var saveProduct = productRepository.save(product);
        return new ProductDTO(saveProduct.getId(),
                saveProduct.getName(),
                saveProduct.getDescription(),
                saveProduct.getPrice(),
                saveProduct.getStock());
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Product product){
        return  new ProductDTO(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock());
    }


    @Override
    public ProductDTO getProductById(Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Product with the id "+id+" not found"));
        return new ProductDTO(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock());
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isEmpty()){
            throw new EntityNotFoundException("Product with the id "+id+" not found");
        }
        var exisitingProduct = new Product();
        exisitingProduct.setName(productDTO.getName());
        exisitingProduct.setDescription(productDTO.getDescription());
        exisitingProduct.setStock(productDTO.getStock());
        exisitingProduct.setPrice(productDTO.getPrice());
        var updatedProduct = productRepository.save(exisitingProduct);
        return new ProductDTO(updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getDescription(),
                updatedProduct.getPrice(),
                updatedProduct.getStock());
    }
}
