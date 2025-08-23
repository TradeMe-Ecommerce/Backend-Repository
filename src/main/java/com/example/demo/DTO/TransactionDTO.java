package com.example.demo.DTO;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionDTO {
    private long id;
    private long userId;
    private String status;
    private BigDecimal price;
    private String paymentMethod;
    private Integer amount;
    private long productId;
    private long orderId;

    public TransactionDTO() {
    }

    public TransactionDTO(long id, long userId, String status, BigDecimal price, String paymentMethod, Integer amount, long productId, long orderId) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.productId = productId;
        this.orderId = orderId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

}
