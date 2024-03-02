package com.kaancan.demoinoca.entity.request.cart;

import java.util.UUID;

public record RemoveProductFromCartRequest(UUID cartId, UUID productId) {
}
