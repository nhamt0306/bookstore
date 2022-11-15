package edu.hcmute.bookstore.mapper;

import edu.hcmute.bookstore.model.AuthorEntity;
import edu.hcmute.bookstore.model.CategoryEntity;
import edu.hcmute.bookstore.model.ProductEntity;
import edu.hcmute.bookstore.model.PublisherEntity;

public class ProductMapper {
    private Long id;
    private String proName;
    private String proDescription;
    private String proContent;
    private Long proPrice;
    private Long proQuantity;
    private Long proSale;
    private String proImage;
    private String category;
    private String author;
    private String publisher;
    private Long curPrice;

    public ProductMapper(Long id, String proName, String proDescription, String proContent, Long proPrice, Long proQuantity, Long proSale, String proImage, CategoryEntity category, AuthorEntity author, PublisherEntity publisher) {
        this.id = id;
        this.proName = proName;
        this.proDescription = proDescription;
        this.proContent = proContent;
        this.proPrice = proPrice;
        this.proQuantity = proQuantity;
        this.proSale = proSale;
        this.proImage = proImage;
        this.category = category.getCatName();
        this.author = author.getAutName();
        this.publisher = publisher.getPubName();
    }

    public ProductMapper(Long id, String proName, String proDescription, String proContent, Long proPrice, Long proQuantity, Long proSale, String proImage, CategoryEntity category, AuthorEntity author, PublisherEntity publisher, Long curPrice) {
        this.id = id;
        this.proName = proName;
        this.proDescription = proDescription;
        this.proContent = proContent;
        this.proPrice = proPrice;
        this.proQuantity = proQuantity;
        this.proSale = proSale;
        this.proImage = proImage;
        this.category = category.getCatName();
        this.author = author.getAutName();
        this.publisher = publisher.getPubName();
        this.curPrice = curPrice;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public void setProDescription(String proDescription) {
        this.proDescription = proDescription;
    }

    public void setProContent(String proContent) {
        this.proContent = proContent;
    }

    public void setProPrice(Long proPrice) {
        this.proPrice = proPrice;
    }

    public void setProQuantity(Long proQuantity) {
        this.proQuantity = proQuantity;
    }

    public void setProSale(Long proSale) {
        this.proSale = proSale;
    }

    public void setProImage(String proImage) {
        this.proImage = proImage;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Long getCurPrice() {
        return curPrice;
    }

    public void setCurPrice(Long curPrice) {
        this.curPrice = curPrice;
    }

    public String getProName() {
        return proName;
    }

    public String getProDescription() {
        return proDescription;
    }

    public String getProContent() {
        return proContent;
    }

    public Long getProPrice() {
        return proPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProQuantity() {
        return proQuantity;
    }

    public Long getProSale() {
        return proSale;
    }

    public String getProImage() {
        return proImage;
    }

    public String getCategory() {
        return category;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }
}
