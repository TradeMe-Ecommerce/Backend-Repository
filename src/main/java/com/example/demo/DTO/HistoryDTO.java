package com.example.demo.DTO;

import java.util.List;
import java.util.Set;

public class HistoryDTO {

    private long id;
    private long userId;
    private Set<OrderDTO> orders;

    public HistoryDTO() {
    }

    public HistoryDTO(long id, long userId, Set<OrderDTO> orders) {
        this.id = id;
        this.userId = userId;
        this.orders = orders;
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

    public Set<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderDTO> orders) {
        this.orders = orders;
    }
}
