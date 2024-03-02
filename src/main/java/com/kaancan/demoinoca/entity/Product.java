package com.kaancan.demoinoca.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kaancan.demoinoca.entity.base.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Table(name = "product")
@Entity
public class Product extends BaseEntityAudit {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "stock")
    private int stock;

    @ManyToMany(mappedBy = "products")
    @JsonManagedReference
    private List<Cart> carts;

}
