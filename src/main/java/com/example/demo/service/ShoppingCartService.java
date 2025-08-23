package com.example.demo.service;

import com.example.demo.DTO.CartListingDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.DTO.ShoppingCartDTO;
import com.example.demo.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCartDTO getCartByUserId(Long userId);
    void addProductToCart(Long userId, Long productId, int quantity);
    void updateProductQuantity(Long userId, Long productId, int quantity);
    void removeProductFromCart(Long userId, Long productId);
    void clearCart(Long userId);

    ShoppingCartDTO createShoppingCart(ShoppingCartDTO shoppingCartDTO);
;
    List<ShoppingCartDTO> findAll();

    List<CartListingDTO> getCartListingsByIds(List<Long> ids);
}
