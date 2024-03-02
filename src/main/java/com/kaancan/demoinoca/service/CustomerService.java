package com.kaancan.demoinoca.service;

import com.kaancan.demoinoca.entity.Cart;
import com.kaancan.demoinoca.entity.Customer;
import com.kaancan.demoinoca.entity.request.AddCustomerRequest;
import com.kaancan.demoinoca.repository.CartRepository;
import com.kaancan.demoinoca.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;

    public CustomerService(CustomerRepository customerRepository,
                           CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }

    @Transactional
    public void addCustomer(AddCustomerRequest addCustomerRequest) {
        Customer customer = new Customer();
        Cart cart = createCart(customer);

        customer.setName(addCustomerRequest.customerName());
        customer.setCountry(addCustomerRequest.country());
        customer.setCart(cart);
        customerRepository.save(customer);
    }

    public Cart createCart(Customer customer) {
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setTotalPrice(0);

        cartRepository.save(cart);
        return cart;
    }


}
