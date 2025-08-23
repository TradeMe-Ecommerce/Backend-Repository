package com.example.demo.controller;
import java.util.List;

import com.example.demo.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.ShoppingCartDTO;

@RestController
@RequestMapping("/api/shopping-cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping
    public List<ShoppingCartDTO> getAllCarts() {
        return shoppingCartService.findAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(shoppingCartService.getCartByUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @DeleteMapping("/{userId}/remove-product")
    public ResponseEntity<?> removeProductFromCart(@PathVariable Long userId, @RequestParam Long productId) {
        try {
            shoppingCartService.removeProductFromCart(userId, productId);
            return ResponseEntity.status(200).body("Product for " + userId + " and product " + productId + " removed from shopping cart.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }

    @PostMapping("/{userId}/add-product")
    public ResponseEntity<?> addProductToCart(@PathVariable Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        try {
            shoppingCartService.addProductToCart(userId, productId, quantity);
            return ResponseEntity.status(201).body("Product for " + userId + " and product " + productId + " added to shopping cart.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }

    @PutMapping("/{userId}/update-product")
    public ResponseEntity<?> updateProductQuantity(@PathVariable Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        try {
            shoppingCartService.updateProductQuantity(userId, productId, quantity);
            return ResponseEntity.status(204).body("Product for " + userId + " and product " + productId + " updated in shopping cart.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }



    @PutMapping("/{userId}/clear")
    public ResponseEntity<?> clearCart(@PathVariable Long userId) {
        try {
            shoppingCartService.clearCart(userId);
            return ResponseEntity.status(204).body("Shopping cart for user " + userId + " cleared.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        try {
            ShoppingCartDTO createdShoppingCart = shoppingCartService.createShoppingCart(shoppingCartDTO);
            return ResponseEntity.status(201).body(createdShoppingCart);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/cart-listings")
    public ResponseEntity<?> getCartListings(@RequestParam List<Long> ids) {
        try {
            return ResponseEntity.ok(shoppingCartService.getCartListingsByIds(ids));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
