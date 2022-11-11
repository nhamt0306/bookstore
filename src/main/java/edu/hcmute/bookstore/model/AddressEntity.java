package edu.hcmute.bookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String name;
    private String phoneNumber;
    private Boolean add_default = true;
    private String note;

    //  Relationship with table User
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userId", nullable = false, referencedColumnName = "id")
    @JsonBackReference
    //category_id is foreign key of SubCategory and referenced to id of table Category
    private UserEntity userEntity;

    // Constructor, Getter and Setter
    public AddressEntity() {
    }

    public AddressEntity(Long id, String address, String name, String phoneNumber, Boolean add_default) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.add_default = add_default;
    }

    public AddressEntity(Long id, String address, String name, String phoneNumber, Boolean add_default, String note) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.add_default = add_default;
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getAdd_default() {
        return add_default;
    }

    public void setAdd_default(Boolean add_default) {
        this.add_default = add_default;
    }
}
