package com.example.demo.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id", length = 64)
    private long productId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "location", nullable = false, length = 64)
    private String location;

    @Column(name = "status", nullable = false, length = 20)
    private String status;


    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(mappedBy = "products")
    private Set<Favorite> favorites;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Image> images;

    @OneToMany(mappedBy = "product")
    private Set<Transaction> transactions;

    @OneToMany(mappedBy = "product")
    private Set<CartListing> cartListings;

    @OneToMany(mappedBy = "product")
    private Set<Inventory> inventories;

    public Product() {
        initializeNecessaryFields();
    }

    public Product(long productId, String name, String description, BigDecimal price, Date date, String location, String status) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.date = date;
        this.location = location;
        this.status = status;
        initializeNecessaryFields();
    }

    public void initializeNecessaryFields() {
        this.categories = new HashSet<>();
        this.images = new HashSet<>();
    }

    public long getId() {
        return productId;
    }

    public void setId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Favorite> favorites) {
        this.favorites = favorites;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Set<CartListing> getCartListings() {
        return cartListings;
    }

    public void setCartListings(Set<CartListing> cartListings) {
        this.cartListings = cartListings;
    }

    public Set<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(Set<Inventory> inventories) {
        this.inventories = inventories;
    }
}