package com.example.demo.controller;
import com.example.demo.DTO.FavoriteDTO;
import com.example.demo.service.FavoriteService;
import com.example.demo.service.impl.FavoriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping
    public ResponseEntity<List<FavoriteDTO>> getAllFavorites() {
        try {
            List<FavoriteDTO> favorites = favoriteService.findAll();
            return ResponseEntity.status(200).body(favorites);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoriteDTO> getFavoriteById(@PathVariable Long id) {
        try {
            FavoriteDTO favorite = favoriteService.findById(id);
            return ResponseEntity.status(200).body(favorite);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<FavoriteDTO> createFavorite(@RequestBody FavoriteDTO favoriteDTO) {
        try {
            FavoriteDTO createdFavorite = favoriteService.createFavorite(favoriteDTO);
            return ResponseEntity.status(201).body(createdFavorite); // 201 Created
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @PatchMapping
    public ResponseEntity<FavoriteDTO> updateFavorite(@RequestBody FavoriteDTO favoriteDTO) {
        try {
            FavoriteDTO updatedFavorite = favoriteService.updateFavorite(favoriteDTO);
            return ResponseEntity.status(200).body(updatedFavorite); // 200 OK
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Long id) {
        try {
            favoriteService.deleteFavorite(id);
            return ResponseEntity.status(204).build(); // 204 No Content
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<FavoriteDTO> getFavoriteByUserId(@PathVariable Long userId) {
        try {
            FavoriteDTO favorite = favoriteService.getFavoriteByUserId(userId);
            return ResponseEntity.status(200).body(favorite);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/{userId}/products/{productId}")
    public ResponseEntity<Void> addProductToFavorite(@PathVariable Long userId, @PathVariable Long productId) {
        try {
            favoriteService.addProductToFavorite(userId, productId);
            return ResponseEntity.status(201).build(); // 201 Created
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{userId}/products/{productId}")
    public ResponseEntity<Void> removeProductFromFavorite(@PathVariable Long userId, @PathVariable Long productId) {
        try {
            favoriteService.removeProductFromFavorite(userId, productId);
            return ResponseEntity.status(204).build(); // 204 No Content
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
