package com.example.demo.service;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAll();
    ProductDTO findById(Long id);
    ProductDTO create(ProductDTO product);
    ProductDTO update(ProductDTO product);
    void delete(Long id);
}
