package edu.hcmute.bookstore.dto;

public class CartProductDTO {
    private Long quantity;
    private Long price;
    private Long productId;

    public CartProductDTO(Long quantity, Long price, Long productId) {
        this.quantity = quantity;
        this.price = price;
        this.productId = productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
