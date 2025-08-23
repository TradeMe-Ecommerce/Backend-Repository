package com.example.demo.DTO;

import java.util.Date;

public class ReviewDTO {
    private long id;
    private long transactionId;
    private Float points;
    private String description;
    private String date;

    public ReviewDTO() {
    }

    public ReviewDTO(long id, long transactionId, Float points, String description, String date) {
        this.id = id;
        this.transactionId = transactionId;
        this.points = points;
        this.description = description;
        this.date = date;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }
    public Float getPoints() {
        return points;
    }
    public void setPoints(Float points) {
        this.points = points;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    

}
