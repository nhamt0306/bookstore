package edu.hcmute.bookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ord_quantity;
    private Long ord_price;
    private String ord_note;
    private Long ord_ship_fee;
    private String ord_status;

    // Relationship with table OrderDetailsEntity
    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<TransactionEntity> orderDetailsEntities;

    // Relationship with table UserEntity (n:1). Because 1 user can have n orders
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userId", nullable = false, referencedColumnName = "id")
    @JsonBackReference
    //category_id is foreign key of SubCategory and referenced to id of table Category
    private UserEntity userEntities; //Relative with MapBy in CategoryEntity


    // Constructor
    public OrderEntity() {
    }

    public OrderEntity(Long id, Long ord_quantity, Long ord_price, String ord_note, Long ord_ship_fee, String ord_status) {
        this.id = id;
        this.ord_quantity = ord_quantity;
        this.ord_price = ord_price;
        this.ord_note = ord_note;
        this.ord_ship_fee = ord_ship_fee;
        this.ord_status = ord_status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrd_quantity() {
        return ord_quantity;
    }

    public void setOrd_quantity(Long ord_quantity) {
        this.ord_quantity = ord_quantity;
    }

    public Long getOrd_price() {
        return ord_price;
    }

    public void setOrd_price(Long ord_price) {
        this.ord_price = ord_price;
    }
}
