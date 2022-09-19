package edu.hcmute.bookstore.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class TransactionEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tran_status;
    private String tran_total_price;

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

    public TransactionEntity(Long id, String tran_status, String tran_total_price) {
        this.id = id;
        this.tran_status = tran_status;
        this.tran_total_price = tran_total_price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTran_status() {
        return tran_status;
    }

    public void setTran_status(String tran_status) {
        this.tran_status = tran_status;
    }

    public String getTran_total_price() {
        return tran_total_price;
    }

    public void setTran_total_price(String tran_total_price) {
        this.tran_total_price = tran_total_price;
    }
}
