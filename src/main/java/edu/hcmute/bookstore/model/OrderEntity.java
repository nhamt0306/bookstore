package edu.hcmute.bookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ordTotalPrice;
    private String ordNote;
    private Long ordShippingFee;
    private String ordPayment;
    private String ordStatus;
    private String ordAddress;
    private String ordPhone;

    // Relationship with table OrderDetailsEntity
    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrdTotalPrice() {
        return ordTotalPrice;
    }

    public void setOrdTotalPrice(Long ordTotalPrice) {
        this.ordTotalPrice = ordTotalPrice;
    }

    public String getOrdNote() {
        return ordNote;
    }

    public void setOrdNote(String ordNote) {
        this.ordNote = ordNote;
    }

    public Long getOrdShippingFee() {
        return ordShippingFee;
    }

    public void setOrdShippingFee(Long ordShippingFee) {
        this.ordShippingFee = ordShippingFee;
    }

    public String getOrdPayment() {
        return ordPayment;
    }

    public void setOrdPayment(String ordPayment) {
        this.ordPayment = ordPayment;
    }

    public String getOrdStatus() {
        return ordStatus;
    }

    public void setOrdStatus(String ordStatus) {
        this.ordStatus = ordStatus;
    }

    public OrderEntity(Long id, Long ordTotalPrice, String ordNote, Long ordShippingFee, String ordPayment, String ordStatus) {
        this.id = id;
        this.ordTotalPrice = ordTotalPrice;
        this.ordNote = ordNote;
        this.ordShippingFee = ordShippingFee;
        this.ordPayment = ordPayment;
        this.ordStatus = ordStatus;
    }

    public String getOrdAddress() {
        return ordAddress;
    }

    public void setOrdAddress(String ordAddress) {
        this.ordAddress = ordAddress;
    }

    public String getOrdPhone() {
        return ordPhone;
    }

    public void setOrdPhone(String ordPhone) {
        this.ordPhone = ordPhone;
    }

    public List<TransactionEntity> getOrderDetailsEntities() {
        return orderDetailsEntities;
    }

    public void setOrderDetailsEntities(List<TransactionEntity> orderDetailsEntities) {
        this.orderDetailsEntities = orderDetailsEntities;
    }

    public UserEntity getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(UserEntity userEntities) {
        this.userEntities = userEntities;
    }

    public OrderEntity(Long ordTotalPrice, String ordNote, Long ordShippingFee, String ordPayment, String ordStatus, String ordAddress, String ordPhone) {
        this.ordTotalPrice = ordTotalPrice;
        this.ordNote = ordNote;
        this.ordShippingFee = ordShippingFee;
        this.ordPayment = ordPayment;
        this.ordStatus = ordStatus;
        this.ordAddress = ordAddress;
        this.ordPhone = ordPhone;
    }
}
