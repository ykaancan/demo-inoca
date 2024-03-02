package com.kaancan.demoinoca.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kaancan.demoinoca.entity.base.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Table(name = "cart")
@Entity
@JsonIgnoreProperties("customer")
public class Cart extends BaseEntityAudit {

    @OneToOne(mappedBy = "cart", fetch = FetchType.LAZY)
    private Customer customer;

    @Column(name = "total_price")
    private int totalPrice;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinTable(name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

}
