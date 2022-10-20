package edu.hcmute.bookstore.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String catName;
    private Long catParent;
    private String catStatus = "Active";

    // Relationship with table ProductEntity
    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL)
    private List<ProductEntity> productEntities = new ArrayList<>();


    //Constructor, Getter and Setter
    public CategoryEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Long getCatParent() {
        return catParent;
    }

    public void setCatParent(Long catParent) {
        this.catParent = catParent;
    }

    public String getCatStatus() {
        return catStatus;
    }

    public void setCatStatus(String catStatus) {
        this.catStatus = catStatus;
    }

    public CategoryEntity(Long id, String catName, Long catParent, String catStatus) {
        this.id = id;
        this.catName = catName;
        this.catParent = catParent;
        this.catStatus = catStatus;
    }
}
