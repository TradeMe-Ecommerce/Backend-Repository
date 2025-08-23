package com.example.demo.service.impl;

import com.example.demo.DTO.ImageDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.mapper.ImageMapper;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import com.example.demo.util.DateFormatter;
import com.sun.source.tree.TryTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found with id: " + id));
        return productMapper.toDto(product);
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        if (productDTO.getName() == null || productDTO.getName().isEmpty()) {
            throw new RuntimeException("Product name is required");
        }
        try {
            Product productEntity = productMapper.toEntity(productDTO);

            Set<Category> validatedCategories = productEntity.getCategories().stream()
                    .map(cat -> categoryRepository.findById(cat.getId())
                            .orElseThrow(() -> new RuntimeException("Category not found: " + cat.getId())))
                    .collect(Collectors.toSet());

            productEntity.setCategories(validatedCategories);
            Product savedProduct = productRepository.save(productEntity);
            return productMapper.toDto(savedProduct);
        } catch (Exception e) {
            System.err.println("Error creating product: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductDTO update(ProductDTO product) {
        Optional<Product> productEntity = productRepository.findById(product.getId());
        if (productEntity.isPresent()){
            Product existingProduct = productEntity.get();
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setDate(DateFormatter.convertStringToDate(product.getDate()));
            existingProduct.setLocation(product.getLocation());
            existingProduct.setStatus(product.getStatus());
            existingProduct.setCategories(
                    product.getCategories().stream()
                            .map(categoryDTO -> categoryRepository.findById(categoryDTO.getId())
                                    .orElseThrow(() -> new RuntimeException("Category not found: " + categoryDTO.getId())))
                            .collect(Collectors.toSet())
            );
            existingProduct.setImages(
                    product.getImages().stream()
                            .map(imageMapper::toEntity)
                            .collect(Collectors.toSet())
            );
            return productMapper.toDto(productRepository.save(existingProduct));
        } else {
            throw new RuntimeException("Product not found" + product.getId());
        }
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }
}
