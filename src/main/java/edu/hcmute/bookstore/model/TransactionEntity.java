package edu.hcmute.bookstore.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class TransactionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tranStatus = "Active";
    private Long tranUnitPrice;
    private Long tranQuantity;

    //  Relationship with table Product
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "productId", nullable = false, referencedColumnName = "id")
    private ProductEntity productEntity;

    //  Relationship with table Order
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "orderId", nullable = false, referencedColumnName = "id")
    private OrderEntity orderEntity;


    // Constructor
    public TransactionEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTranStatus() {
        return tranStatus;
    }

    public void setTranStatus(String tranStatus) {
        this.tranStatus = tranStatus;
    }

    public Long getTranUnitPrice() {
        return tranUnitPrice;
    }

    public void setTranUnitPrice(Long tranUnitPrice) {
        this.tranUnitPrice = tranUnitPrice;
    }

    public Long getTranQuantity() {
        return tranQuantity;
    }

    public void setTranQuantity(Long tranQuantity) {
        this.tranQuantity = tranQuantity;
    }

    public TransactionEntity(Long tranUnitPrice, Long tranQuantity) {
        this.tranUnitPrice = tranUnitPrice;
        this.tranQuantity = tranQuantity;
    }

    public TransactionEntity(Long tranUnitPrice, Long tranQuantity, ProductEntity productEntity) {
        this.tranUnitPrice = tranUnitPrice;
        this.tranQuantity = tranQuantity;
        this.productEntity = productEntity;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public TransactionEntity(Long id, String tranStatus, Long tranUnitPrice, Long tranQuantity) {
        this.id = id;
        this.tranStatus = tranStatus;
        this.tranUnitPrice = tranUnitPrice;
        this.tranQuantity = tranQuantity;
    }
}
