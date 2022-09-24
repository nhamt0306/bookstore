package edu.hcmute.bookstore.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "wishlists")
public class WishListEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //  Relationship with table Product
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "productId", nullable = false, referencedColumnName = "id")
    private ProductEntity productEntity;

    //  Relationship with table User
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userId", nullable = false, referencedColumnName = "id")
    private UserEntity userEntity;

    //Constructor, Getter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WishListEntity() {
    }

    public WishListEntity(Long id) {
        this.id = id;
    }
}
