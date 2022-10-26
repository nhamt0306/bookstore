package edu.hcmute.bookstore.dto;

public class ProductDTO {
    private String proName;
    private String proDescription;
    private String proContent;
    private Long proPrice;
    private Long proQuantity;
    private Long proSale;
    private String proImage;
    private Long categoryId;
    private Long authorId;
    private Long publisherId;

    public ProductDTO(String proName, String proDescription, String proContent, Long proPrice, Long proQuantity, Long proSale, String proImage) {
        this.proName = proName;
        this.proDescription = proDescription;
        this.proContent = proContent;
        this.proPrice = proPrice;
        this.proQuantity = proQuantity;
        this.proSale = proSale;
        this.proImage = proImage;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProDescription() {
        return proDescription;
    }

    public void setProDescription(String proDescription) {
        this.proDescription = proDescription;
    }

    public String getProContent() {
        return proContent;
    }

    public void setProContent(String proContent) {
        this.proContent = proContent;
    }

    public Long getProPrice() {
        return proPrice;
    }

    public void setProPrice(Long proPrice) {
        this.proPrice = proPrice;
    }

    public Long getProQuantity() {
        return proQuantity;
    }

    public void setProQuantity(Long proQuantity) {
        this.proQuantity = proQuantity;
    }

    public Long getProSale() {
        return proSale;
    }

    public void setProSale(Long proSale) {
        this.proSale = proSale;
    }

    public String getProImage() {
        return proImage;
    }

    public void setProImage(String proImage) {
        this.proImage = proImage;
    }
}
