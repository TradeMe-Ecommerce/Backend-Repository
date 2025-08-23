package com.example.demo.DTO;

import com.example.demo.entity.OrderStatus;

import java.util.List;

public class OrderDTO {

    private long id;
    private Integer orderNumber;
    private String date;
    private String status;
    private Long historyId;
    private List<Long> transactionIds;

    public OrderDTO() {
    }

    public OrderDTO(long id, String date, Integer orderNumber, String status, Long historyId, List<Long> transactionIds) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.status = status;
        this.historyId = historyId;
        this.transactionIds = transactionIds;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public List<Long> getTransactionIds() {
        return transactionIds;
    }

    public void setTransactionIds(List<Long> transactionIds) {
        this.transactionIds = transactionIds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
