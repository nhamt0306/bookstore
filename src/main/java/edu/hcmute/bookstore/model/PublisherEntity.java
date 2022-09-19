package edu.hcmute.bookstore.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publishers")
public class PublisherEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pub_name;
    private String pub_phone;
    private String pub_email;
    private String pub_address;
    private String pub_status = "Active";

    // Relationship with table PublisherEntity
    @OneToMany(mappedBy = "publisherEntity", cascade = CascadeType.ALL)
    private List<ProductEntity> productEntities =new ArrayList<>();

    //Constructor, Getter and Setter

    public PublisherEntity() {
    }

    public PublisherEntity(Long id, String pub_name, String pub_phone, String pub_email, String pub_address, String pub_status) {
        this.id = id;
        this.pub_name = pub_name;
        this.pub_phone = pub_phone;
        this.pub_email = pub_email;
        this.pub_address = pub_address;
        this.pub_status = pub_status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPub_name() {
        return pub_name;
    }

    public void setPub_name(String pub_name) {
        this.pub_name = pub_name;
    }

    public String getPub_phone() {
        return pub_phone;
    }

    public void setPub_phone(String pub_phone) {
        this.pub_phone = pub_phone;
    }

    public String getPub_email() {
        return pub_email;
    }

    public void setPub_email(String pub_email) {
        this.pub_email = pub_email;
    }

    public String getPub_address() {
        return pub_address;
    }

    public void setPub_address(String pub_address) {
        this.pub_address = pub_address;
    }

    public String getPub_status() {
        return pub_status;
    }

    public void setPub_status(String pub_status) {
        this.pub_status = pub_status;
    }
}
