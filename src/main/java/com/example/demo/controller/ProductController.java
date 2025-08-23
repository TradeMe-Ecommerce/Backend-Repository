package com.example.demo.controller;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.service.ProductService;
import com.example.demo.service.impl.ProductServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable long id) {
        try {
            ProductDTO product = productService.findById(id);
            return ResponseEntity.status(200).body(product);
        }
        catch (Exception e) {
            System.out.println(e.getStackTrace());
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAllProducts() {
        try {
            List<ProductDTO> products = productService.findAll();
            return ResponseEntity.status(200).body(products);
        }
        catch (Exception e) {
            System.out.println(e.getStackTrace());
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            ProductDTO createdProduct = productService.create(productDTO);
            return ResponseEntity.status(201).body(createdProduct);
        }
        catch (Exception e) {
            System.out.println(e.getStackTrace());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PatchMapping
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) {
        try {
            return ResponseEntity.status(200).body(productService.update(productDTO));
        }
        catch (Exception e) {
            System.out.println(e.getStackTrace());
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        try {
            productService.delete(id);
            return ResponseEntity.status(204).build();
        }
        catch (Exception e) {
            System.out.println(e.getStackTrace());
            return ResponseEntity.status(500).build();
        }
    }
}
