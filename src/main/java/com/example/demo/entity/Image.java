package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id", length = 64)
    private long imageId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    //@Lob
    @Column(name = "image", nullable = false)
    private String image;

    public Image() {
        this.image = "empty";
    }

    public Image(long imageId, Product product, String image) {
        this.imageId = imageId;
        this.product = product;
        this.image = image;
    }


    public long getId() {
        return imageId;
    }

    public void setId(long imageId) {
        this.imageId = imageId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}