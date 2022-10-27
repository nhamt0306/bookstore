package edu.hcmute.bookstore.mapper;

import edu.hcmute.bookstore.model.AuthorEntity;
import edu.hcmute.bookstore.model.CategoryEntity;
import edu.hcmute.bookstore.model.ProductEntity;
import edu.hcmute.bookstore.model.PublisherEntity;

public class ProductMapper {
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

    public ProductMapper(String proName, String proDescription, String proContent, Long proPrice, Long proQuantity, Long proSale, String proImage, CategoryEntity category, AuthorEntity author, PublisherEntity publisher) {
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
