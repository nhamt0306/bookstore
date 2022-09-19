package edu.hcmute.bookstore.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pro_name;
    private String pro_description;
    private String pro_content;
    private Long pro_price;
    private String pro_image;
    private String pro_status = "Active";

    // Relationship with table TransactionEntity
    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactionEntities =new ArrayList<>();

    // Constructor, Getter, Setter
    public ProductEntity() {
    }

    public ProductEntity(Long id, String pro_name, String pro_description, String pro_content, Long pro_price, String pro_image, String pro_status) {
        this.id = id;
        this.pro_name = pro_name;
        this.pro_description = pro_description;
        this.pro_content = pro_content;
        this.pro_price = pro_price;
        this.pro_image = pro_image;
        this.pro_status = pro_status;
    }

    public String getPro_status() {
        return pro_status;
    }

    public void setPro_status(String pro_status) {
        this.pro_status = pro_status;
    }

    public String getPro_image() {
        return pro_image;
    }

    public void setPro_image(String pro_image) {
        this.pro_image = pro_image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public String getPro_description() {
        return pro_description;
    }

    public void setPro_description(String pro_description) {
        this.pro_description = pro_description;
    }

    public String getPro_content() {
        return pro_content;
    }

    public void setPro_content(String pro_content) {
        this.pro_content = pro_content;
    }

    public Long getPro_price() {
        return pro_price;
    }

    public void setPro_price(Long pro_price) {
        this.pro_price = pro_price;
    }
}
