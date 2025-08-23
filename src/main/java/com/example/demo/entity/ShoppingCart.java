package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shopping_cart_id", length = 128)
    private long shoppingCartId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    private Set<CartListing> cartListings = new HashSet<>();

    public ShoppingCart() {
    }

    public ShoppingCart(long shoppingCartId, User user) {
        this.shoppingCartId = shoppingCartId;
        this.user = user;
    }

    public long getId() {
        return shoppingCartId;
    }

    public void setId(long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<CartListing> getCartListings() {
        return cartListings;
    }

    public void setCartListings(Set<CartListing> cartListings) {
        this.cartListings = cartListings;
    }

    public List<Product> getProducts() {
        return cartListings.stream()
                .map(CartListing::getProduct)
                .toList();
    }

    public void removeListing(CartListing listing) {
        cartListings.remove(listing);
    }
}