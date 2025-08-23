package com.example.demo.DTO;

public class InventoryDTO {

    private long id;
    private long productId;
    private long userId;
    private int stock;

    public InventoryDTO() {
    }

    public InventoryDTO(long id, long productId, long userId, int stock) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.stock = stock;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
