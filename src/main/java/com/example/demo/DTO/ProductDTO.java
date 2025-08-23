package com.example.demo.DTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ProductDTO {
    private long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String date;
    private String location;
    private String status;
    private Set<CategoryDTO> categories;
    private List<ImageDTO> images;

    public ProductDTO() {
    }

    public ProductDTO(long id, String name, String description, BigDecimal price, String date, String location, String status, Set<CategoryDTO> categories, List<ImageDTO> images) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.date = date;
        this.location = location;
        this.status = status;
        this.categories = categories;
        this.images = images;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public Set<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryDTO> categories) {
        this.categories = categories;
    }

    public List<ImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                ", categories=" + categories +
                ", images=" + images +
                '}';
    }
}
