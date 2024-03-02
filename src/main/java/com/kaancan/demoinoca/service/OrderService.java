package com.kaancan.demoinoca.service;

import com.kaancan.demoinoca.entity.Cart;
import com.kaancan.demoinoca.entity.Order;
import com.kaancan.demoinoca.entity.OrderDetail;
import com.kaancan.demoinoca.entity.Product;
import com.kaancan.demoinoca.entity.request.order.PlaceOrderRequest;
import com.kaancan.demoinoca.repository.CartRepository;
import com.kaancan.demoinoca.repository.OrderDetailRepository;
import com.kaancan.demoinoca.repository.OrderRepository;
import com.kaancan.demoinoca.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderService(OrderRepository orderRepository,
                        CartRepository cartRepository,
                        ProductRepository productRepository,
                        OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.orderDetailRepository = orderDetailRepository;

    }

    public List<Order> getAllForCustomer(UUID customerId) {
        return orderRepository.findAllByCustomerId(customerId);
    }

    @Transactional
    public void placeOrder(PlaceOrderRequest placeOrderRequest) {
        Cart cart = cartRepository.findCartByCustomerId(placeOrderRequest.customerId());
        Map<UUID, Long> productCountMap = cart.getProducts().stream().collect(Collectors.groupingBy(Product::getId, Collectors.counting()));
        List<Product> products = productRepository.findAll();

        List<OrderDetail> orderDetails = new ArrayList<>();

        for (Product product : products) {
            Long countInCart = productCountMap.get(product.getId());

            if (countInCart != null) {
                int remainingStock = product.getStock() - countInCart.intValue();
                if (remainingStock >= 0) {
                    product.setStock(remainingStock);
                }
                else {
                    throw new RuntimeException("Insufficient stock for product:" + product.getName());
                }

                productRepository.save(product);

                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setQuantity(countInCart.intValue());
                orderDetail.setPriceAtPurchase(product.getPrice());
                orderDetails.add(orderDetail);
            }
        }


        Order order = new Order();
        order.setCustomer(cart.getCustomer());
        order.setTotalPrice(cart.getTotalPrice());
        orderRepository.save(order);

        orderDetails.forEach(o -> o.setOrder(order));
        orderDetailRepository.saveAll(orderDetails);

        cart.getProducts().clear();
        cart.setTotalPrice(0);
        cartRepository.save(cart);
    }

}
