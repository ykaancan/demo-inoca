package com.kaancan.demoinoca.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kaancan.demoinoca.entity.base.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Table(name = "customer")
@Entity
@JsonIgnoreProperties("cart")
public class Customer extends BaseEntityAudit {

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<Order> orders;
}
