package edu.hcmute.bookstore.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cat_name;
    private Long cat_parent;
    private String pub_status = "Active";

    // Relationship with table ProductEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "productId", nullable = false, referencedColumnName = "id")
    private ProductEntity productEntity;

    //Constructor, Getter and Setter
    public CategoryEntity() {
    }

    public CategoryEntity(Long id, String cat_name, Long cat_parent, String pub_status) {
        this.id = id;
        this.cat_name = cat_name;
        this.cat_parent = cat_parent;
        this.pub_status = pub_status;
    }

    public String getPub_status() {
        return pub_status;
    }

    public void setPub_status(String pub_status) {
        this.pub_status = pub_status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public Long getCat_parent() {
        return cat_parent;
    }

    public void setCat_parent(Long cat_parent) {
        this.cat_parent = cat_parent;
    }
}
