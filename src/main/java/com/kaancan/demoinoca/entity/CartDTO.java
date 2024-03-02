package com.kaancan.demoinoca.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CartDTO {

    private Set<String> productNames;
    private int totalPrice;
    private Date createdAt;
    private Date updatedAt;
}
