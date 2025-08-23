package com.example.demo.DTO;

public class ImageDTO {
    private long id;
    private long productId;
    private String image;

    public ImageDTO() {
    }

    public ImageDTO(long id, long productId, String image) {
        this.id = id;
        this.productId = productId;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
