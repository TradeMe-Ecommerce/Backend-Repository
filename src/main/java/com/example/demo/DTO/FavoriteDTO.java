package com.example.demo.DTO;

import java.util.List;

public class FavoriteDTO {

    private long id;
    private long userId;
    private List<Long> productIds;


    public FavoriteDTO() {
    }

    public FavoriteDTO(long id, long userId, List<Long> productIds) {
        this.id = id;
        this.userId = userId;
        this.productIds = productIds;
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

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}

