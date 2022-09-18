package edu.hcmute.bookstore.model;

import javax.persistence.*;

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
