package com.example.demo.DTO;

import java.util.List;
import java.util.Set;


public class ShoppingCartDTO {
    private long id;
    private long userId;
    private Set<CartListingDTO> cartListings;

    public ShoppingCartDTO() {
    }

    public ShoppingCartDTO(long id, long userId, Set<CartListingDTO> cartListings ) {
        this.id = id;
        this.userId = userId;
        this.cartListings = cartListings;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Set<CartListingDTO> getCartListings() {
        return cartListings;
    }

    public void setCartListings(Set<CartListingDTO> cartListingsIds) {
        this.cartListings = cartListingsIds;
    }
}
