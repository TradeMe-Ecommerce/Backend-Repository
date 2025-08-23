package com.example.demo.DTO;

import java.util.Date;
import java.util.List;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String date;
    private List<Long> inventoryIds;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String email, String phone, String date) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Long> getInventoryIds() {
        return inventoryIds;
    }

    public void setInventoryIds(List<Long> inventoryIds) {
        this.inventoryIds = inventoryIds;
    }
}