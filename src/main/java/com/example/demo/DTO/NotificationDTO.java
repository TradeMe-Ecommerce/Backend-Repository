package com.example.demo.DTO;

import java.util.Date;

public class NotificationDTO {

    private long id;
    private long userId;
    private String date;
    private String description;

    public NotificationDTO() {
    }

    public NotificationDTO(long id, long userId, String date, String description) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.description = description;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
