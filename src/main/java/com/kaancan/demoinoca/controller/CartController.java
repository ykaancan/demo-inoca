package com.kaancan.demoinoca.controller;

import com.kaancan.demoinoca.converter.CartDTOConverter;
import com.kaancan.demoinoca.entity.Cart;
import com.kaancan.demoinoca.entity.CartDTO;
import com.kaancan.demoinoca.entity.Product;
import com.kaancan.demoinoca.entity.request.cart.AddProductToCartRequest;
import com.kaancan.demoinoca.entity.request.cart.RemoveProductFromCartRequest;
import com.kaancan.demoinoca.service.CartService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final CartDTOConverter cartDTOConverter;

    public CartController(CartService cartService, CartDTOConverter cartDTOConverter) {
        this.cartService = cartService;
        this.cartDTOConverter = cartDTOConverter;
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getById(@PathVariable("cartId") UUID cartId) {
        Cart cart = cartService.getByCartId(cartId);
        return ResponseEntity.ok(cartDTOConverter.toCartDTO(cart));
    }

    @PutMapping("/empty/{cartId}")
    public ResponseEntity<HttpStatus> emptyCart(@PathVariable("cartId") UUID cartId) {
        cartService.emptyCart(cartId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addProduct")
    public ResponseEntity<HttpStatus> addProductToCart(@RequestBody AddProductToCartRequest addProductToCartRequest) {
        cartService.addProductToCart(addProductToCartRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/removeProduct")
    public ResponseEntity<HttpStatus> removeProductFromCart(@RequestBody RemoveProductFromCartRequest removeProductFromCartRequest) {
        cartService.removeProductFromCart(removeProductFromCartRequest);
        return ResponseEntity.ok().build();
    }
}
