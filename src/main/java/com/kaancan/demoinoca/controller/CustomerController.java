package com.kaancan.demoinoca.controller;

import com.kaancan.demoinoca.entity.request.AddCustomerRequest;
import com.kaancan.demoinoca.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    private CustomerService customerService;


    @PostMapping(value = "/insert")
    public ResponseEntity<HttpStatus> addCustomer(@RequestBody AddCustomerRequest addCustomerRequest) {
        customerService.addCustomer(addCustomerRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
