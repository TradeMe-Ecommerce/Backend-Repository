package com.example.demo.DTO;

public class CartListingDTO {

    private long id;
    private long shoppingCartId;
    private long productId;
    private int quantity;

    public CartListingDTO() {
    }

    public CartListingDTO(long id, long shoppingCartId  ,long productId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.shoppingCartId = shoppingCartId;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }
}
