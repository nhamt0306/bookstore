package edu.hcmute.bookstore.dto;

public class CartProductReponse {
    private Long quantity;
    private Long price;
    private String proImage;
    private String proName;
    private String proAuthor;

    public CartProductReponse(Long quantity, Long price, String proImage, String proName, String proAuthor) {
        this.quantity = quantity;
        this.price = price;
        this.proImage = proImage;
        this.proName = proName;
        this.proAuthor = proAuthor;
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

    public String getProImage() {
        return proImage;
    }

    public void setProImage(String proImage) {
        this.proImage = proImage;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProAuthor() {
        return proAuthor;
    }

    public void setProAuthor(String proAuthor) {
        this.proAuthor = proAuthor;
    }
}
