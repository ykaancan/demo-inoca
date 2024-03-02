package com.kaancan.demoinoca.entity.request.cart;

import java.util.UUID;

public record AddProductToCartRequest(UUID cartId, UUID productId) {
}
