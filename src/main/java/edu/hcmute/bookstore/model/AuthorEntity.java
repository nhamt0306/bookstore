package edu.hcmute.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "authors")
public class AuthorEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String autName;
    private String autPhone;
    private String autEmail;
    private String autBiography;
    private String autAddress;
    private String autStatus = "Active";

    // Relationship with table ProductEntity
    @OneToOne(mappedBy = "authorEntity", fetch = FetchType.LAZY)
    @JsonIgnore
    private ProductEntity productEntity;

    // Constructor
    public AuthorEntity() {
    }

    public AuthorEntity(Long id, String autName, String autPhone, String autEmail, String autBiography, String autAddress, String autStatus) {
        this.id = id;
        this.autName = autName;
        this.autPhone = autPhone;
        this.autEmail = autEmail;
        this.autBiography = autBiography;
        this.autAddress = autAddress;
        this.autStatus = autStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutName() {
        return autName;
    }

    public void setAutName(String autName) {
        this.autName = autName;
    }

    public String getAutPhone() {
        return autPhone;
    }

    public void setAutPhone(String autPhone) {
        this.autPhone = autPhone;
    }

    public String getAutEmail() {
        return autEmail;
    }

    public void setAutEmail(String autEmail) {
        this.autEmail = autEmail;
    }

    public String getAutBiography() {
        return autBiography;
    }

    public void setAutBiography(String autBiography) {
        this.autBiography = autBiography;
    }

    public String getAutAddress() {
        return autAddress;
    }

    public void setAutAddress(String autAddress) {
        this.autAddress = autAddress;
    }

    public String getAutStatus() {
        return autStatus;
    }

    public void setAutStatus(String autStatus) {
        this.autStatus = autStatus;
    }
}
