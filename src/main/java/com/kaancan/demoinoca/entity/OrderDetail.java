package com.kaancan.demoinoca.entity;

import com.kaancan.demoinoca.entity.base.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "order_detail")
@Entity
public class OrderDetail extends BaseEntityAudit {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_at_purchase")
    private int priceAtPurchase;
}
