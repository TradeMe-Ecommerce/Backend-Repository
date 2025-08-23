package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cart_listing")
public class CartListing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_listing_id", length = 64)
    private long cartListingId;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "cuantity", nullable = false)
    private Integer quantity;

    public CartListing() {
    }

    public CartListing(long id, ShoppingCart shoppingCart, Product product, Integer quantity) {
        this.shoppingCart = shoppingCart;
        this.product = product;
        this.quantity = quantity;
    }

    public long getId() {
        return cartListingId;
    }

    public void setId(long cartListingId) {
        this.cartListingId = cartListingId;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartListing that)) return false;
        return cartListingId == that.cartListingId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartListingId);
    }
}


