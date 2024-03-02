package com.kaancan.demoinoca.converter;

import com.kaancan.demoinoca.entity.Cart;
import com.kaancan.demoinoca.entity.CartDTO;
import com.kaancan.demoinoca.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class CartDTOConverter {
    public CartDTO toCartDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();

        Set<String> productNames = cart.getProducts().stream()
                .map(Product::getName)
                .collect(Collectors.toSet());

        cartDTO.setProductNames(productNames);
        cartDTO.setTotalPrice(cart.getTotalPrice());

        return cartDTO;
    }
}
