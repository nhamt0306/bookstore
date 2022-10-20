package edu.hcmute.bookstore.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String proName;
    private String proDescription;
    private String proContent;
    private Long proPrice;
    private Long proQuantity;
    private Long proSale;
    private String proImage;
    private Long proAvgRating;
    private String proStatus = "Active";

    // Relationship with table TransactionEntity
    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactionEntities = new ArrayList<>();

    // Relationship with table CartProductEntity
    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    private List<CartProductEntity> cartProductEntities = new ArrayList<>();

    // Relationship with table CategoryEntity
//    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
//    private List<CategoryEntity> categoryEntities = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id")
    private CategoryEntity categoryEntity;


    // Relationship with table CommentEntity
    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    private List<CommentEntity> commentEntities = new ArrayList<>();


    // Relationship with table PublisherEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "publisher_id", nullable = false, referencedColumnName = "id")
    private PublisherEntity publisherEntity;

    // Relationship with table AuthorEntity
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authorId", referencedColumnName = "id")
    private AuthorEntity authorEntity; // mappedBy in table AuthorEntity

    // Relationship with table WishList
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<WishListEntity> wishListEntities = new ArrayList<>();

    // Constructor, Getter, Setter
    public ProductEntity() {
    }

    public ProductEntity(Long id, String proName, String proDescription, String proContent, Long proPrice, Long proQuantity, Long proSale, String proImage, Long proAvgRating, String proStatus) {
        this.id = id;
        this.proName = proName;
        this.proDescription = proDescription;
        this.proContent = proContent;
        this.proPrice = proPrice;
        this.proQuantity = proQuantity;
        this.proSale = proSale;
        this.proImage = proImage;
        this.proAvgRating = proAvgRating;
        this.proStatus = proStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProDescription() {
        return proDescription;
    }

    public void setProDescription(String proDescription) {
        this.proDescription = proDescription;
    }

    public String getProContent() {
        return proContent;
    }

    public void setProContent(String proContent) {
        this.proContent = proContent;
    }

    public Long getProPrice() {
        return proPrice;
    }

    public void setProPrice(Long proPrice) {
        this.proPrice = proPrice;
    }

    public Long getProQuantity() {
        return proQuantity;
    }

    public void setProQuantity(Long proQuantity) {
        this.proQuantity = proQuantity;
    }

    public Long getProSale() {
        return proSale;
    }

    public void setProSale(Long proSale) {
        this.proSale = proSale;
    }

    public String getProImage() {
        return proImage;
    }

    public void setProImage(String proImage) {
        this.proImage = proImage;
    }

    public Long getProAvgRating() {
        return proAvgRating;
    }

    public void setProAvgRating(Long proAvgRating) {
        this.proAvgRating = proAvgRating;
    }

    public String getProStatus() {
        return proStatus;
    }

    public void setProStatus(String proStatus) {
        this.proStatus = proStatus;
    }
}
