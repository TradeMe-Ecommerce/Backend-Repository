package com.example.demo.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "inventory_id", length = 64)
    private long inventoryId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "stock", nullable = false)
    private Integer stock;


    public Inventory() {
    }

    public Inventory(long inventoryId, User user, Product product, Integer stock) {
        this.inventoryId = inventoryId;
        this.user = user;
        this.product = product;
        this.stock = stock;
    }


    public long getId() {
        return inventoryId;
    }

    public void setId(long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
