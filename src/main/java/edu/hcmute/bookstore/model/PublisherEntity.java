package edu.hcmute.bookstore.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publishers")
public class PublisherEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pubName;
    private String pubPhone;
    private String pubEmail;
    private String pubAddress;
    private String pubStatus = "Active";

    // Relationship with table PublisherEntity
    @OneToMany(mappedBy = "publisherEntity", cascade = CascadeType.ALL)
    private List<ProductEntity> productEntities = new ArrayList<>();

    //Constructor, Getter and Setter

    public PublisherEntity() {
    }

    public PublisherEntity(Long id, String pubName, String pubPhone, String pubEmail, String pubAddress, String pubStatus) {
        this.id = id;
        this.pubName = pubName;
        this.pubPhone = pubPhone;
        this.pubEmail = pubEmail;
        this.pubAddress = pubAddress;
        this.pubStatus = pubStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPubName() {
        return pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
    }

    public String getPubPhone() {
        return pubPhone;
    }

    public void setPubPhone(String pubPhone) {
        this.pubPhone = pubPhone;
    }

    public String getPubEmail() {
        return pubEmail;
    }

    public void setPubEmail(String pubEmail) {
        this.pubEmail = pubEmail;
    }

    public String getPubAddress() {
        return pubAddress;
    }

    public void setPubAddress(String pubAddress) {
        this.pubAddress = pubAddress;
    }

    public String getPubStatus() {
        return pubStatus;
    }

    public void setPubStatus(String pubStatus) {
        this.pubStatus = pubStatus;
    }
}
