package com.example.customerrest.controller;

import com.example.customerrest.ds.Customer;
import com.example.customerrest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/creation")
    public String init(){

        List.of(
                new Customer("TH","Thomas","Hardy","thomas@gmail.com"),
                new Customer("JW","John","William","william@gmail.com"),
                new Customer("JD","John","Doe","john@gmail.com")
        )
                .forEach(customerService::saveCustomer);
        return "success";
    }

    @GetMapping("/customers/all")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Customer> listAllCustomers(){
        return customerService.listCustomers();
    }

    @GetMapping("/customers")
    public ResponseEntity<Iterable<Customer>> listCustomers(){
        return ResponseEntity.ok()
                .body(customerService.listCustomers());
    }
}
