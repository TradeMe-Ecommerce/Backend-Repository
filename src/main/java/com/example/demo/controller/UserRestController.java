package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.UserDTO;
import com.example.demo.service.UserServiceRest;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserServiceRest userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try {
            List<UserDTO> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        try {
            return ResponseEntity.ok(userService.getUserByEmail(email));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(201).body(createdUser);
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/{userId}/add-inventory")
    public ResponseEntity<?> addInventoryToUser(@PathVariable Long userId, @RequestParam Long productId, @RequestParam int stock) {
        try {
            userService.addInventoryToUser(userId, productId, stock);
            return ResponseEntity.status(201).body("Inventory added for user " + userId + " and product " + productId);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}/remove-inventory")
    public ResponseEntity<?> removeInventoryFromUser(@PathVariable Long userId, @RequestParam Long productId) {
        try {
            userService.removeInventoryFromUser(userId, productId);
            return ResponseEntity.status(200).body("Inventory removed for user " + userId + " and product " + productId);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/{userId}/update-inventory")
    public ResponseEntity<?> updateInventoryForUser(@PathVariable Long userId, @RequestParam Long productId, @RequestParam int stock) {
        try {
            userService.updateInventoryForUser(userId, productId, stock);
            return ResponseEntity.status(200).body("Inventory updated for user " + userId + " and product " + productId);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<?>> searchUsers(@RequestParam("q") String query) {
        try {
            List<UserDTO> users = userService.searchUsers(query);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }    
    }

    @GetMapping("/{userId}/inventories")
    public ResponseEntity<?> getUserInventories(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(userService.getUserInventories(userId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/inventories/productid")
    public ResponseEntity<?> getInventoriesByProductId(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(userService.getInventoriesByProductId(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
