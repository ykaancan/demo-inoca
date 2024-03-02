package com.kaancan.demoinoca.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kaancan.demoinoca.entity.base.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "customer_order")
@Entity
public class Order extends BaseEntityAudit {

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "total_price")
    private int totalPrice;

}
