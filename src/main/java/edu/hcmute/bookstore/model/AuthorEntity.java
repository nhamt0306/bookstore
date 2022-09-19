package edu.hcmute.bookstore.model;

import javax.persistence.*;

@Entity
@Table(name = "authors")
public class AuthorEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String aut_name;
    private String aut_phone;
    private String aut_email;
    private String aut_biography;
    private String aut_address;
    private String aut_status = "Active";

    // Relationship with table ProductEntity
    @OneToOne(mappedBy = "authorEntity")
    private ProductEntity productEntity;

    // Constructor
    public AuthorEntity() {
    }

    public AuthorEntity(Long id, String aut_name, String aut_phone, String aut_email, String aut_biography, String aut_address, String aut_status) {
        this.id = id;
        this.aut_name = aut_name;
        this.aut_phone = aut_phone;
        this.aut_email = aut_email;
        this.aut_biography = aut_biography;
        this.aut_address = aut_address;
        this.aut_status = aut_status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAut_name() {
        return aut_name;
    }

    public void setAut_name(String aut_name) {
        this.aut_name = aut_name;
    }

    public String getAut_phone() {
        return aut_phone;
    }

    public void setAut_phone(String aut_phone) {
        this.aut_phone = aut_phone;
    }

    public String getAut_email() {
        return aut_email;
    }

    public void setAut_email(String aut_email) {
        this.aut_email = aut_email;
    }

    public String getAut_biography() {
        return aut_biography;
    }

    public void setAut_biography(String aut_biography) {
        this.aut_biography = aut_biography;
    }

    public String getAut_address() {
        return aut_address;
    }

    public void setAut_address(String aut_address) {
        this.aut_address = aut_address;
    }
}
