package com.example.demo.controller;

import com.example.demo.DTO.ImageDTO;
import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<ImageDTO> create(@RequestBody ImageDTO dto) {
        ImageDTO saved = imageService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved); // 201
    }

    @GetMapping
    public ResponseEntity<List<ImageDTO>> findAll() {                 // 200
        try {
            List<ImageDTO> images = imageService.findAll();
            return ResponseEntity.ok(images);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDTO> findById(@PathVariable Long id) { // 200
        try {
            ImageDTO image = imageService.findById(id);
            return ResponseEntity.ok(image);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ImageDTO>> findByProduct(@PathVariable Long productId) { // 200
        try {
            List<ImageDTO> images = imageService.findByProductId(productId);
            return ResponseEntity.ok(images);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PatchMapping
    public ResponseEntity<ImageDTO> update(@RequestBody ImageDTO dto) { // 200
        try {
            ImageDTO updated = imageService.update(dto);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        imageService.delete(id);
        return ResponseEntity.noContent().build();     // 204
    }
}
