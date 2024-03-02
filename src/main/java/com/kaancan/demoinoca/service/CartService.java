package com.kaancan.demoinoca.service;

import com.kaancan.demoinoca.entity.Cart;
import com.kaancan.demoinoca.entity.CartDTO;
import com.kaancan.demoinoca.entity.Product;
import com.kaancan.demoinoca.entity.request.cart.AddProductToCartRequest;
import com.kaancan.demoinoca.entity.request.cart.RemoveProductFromCartRequest;
import com.kaancan.demoinoca.repository.CartRepository;
import com.kaancan.demoinoca.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart getByCartId(UUID cartId) {
        return cartRepository.findById(cartId).orElseThrow();

    }

    @Transactional
    public void emptyCart(UUID cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        cart.getProducts().clear();
        cartRepository.save(cart);
    }

    @Transactional
    public void addProductToCart(AddProductToCartRequest addProductToCartRequest) {
        Cart cart = cartRepository.findById(addProductToCartRequest.cartId()).orElseThrow();
        Product product = productRepository.findById(addProductToCartRequest.productId()).orElseThrow();
        int totalPrice = cart.getTotalPrice() + product.getPrice();
        cart.getProducts().add(product);
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);

    }

    @Transactional
    public void removeProductFromCart(RemoveProductFromCartRequest removeProductFromCartRequest) {
        Cart cart = cartRepository.findById(removeProductFromCartRequest.cartId()).orElseThrow();

        if (cart.getProducts().isEmpty()) {
            throw new RuntimeException("This cart is empty!");
        }

        Product productToRemove = cart.getProducts().stream()
                .filter(product -> product.getId().equals(removeProductFromCartRequest.productId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product with ID " + removeProductFromCartRequest.productId() + " not found in cart"));

        int totalPrice = cart.getTotalPrice() - productToRemove.getPrice();
        cart.getProducts().remove(productToRemove);
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);
    }

}
