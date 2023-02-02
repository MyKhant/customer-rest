package com.example.customerrest.controller;

import com.example.customerrest.ds.Customer;
import com.example.customerrest.ds.CustomerDto;
import com.example.customerrest.service.CustomerService;
import org.apache.catalina.loader.ResourceEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
                new CustomerDto(null,"TH","Thomas","Hardy","thomas@gmail.com"),
                new CustomerDto(null,"JW","John","William","william@gmail.com"),
                new CustomerDto(null,"JD","John","Doe","john@gmail.com")
        )
                .forEach(customerService::saveCustomer);
        return "success";
    }

    @GetMapping(value = "/customers/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> listAllCustomers(){
        return customerService.listCustomers();
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> listCustomers(){
        return ResponseEntity
                .accepted()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(customerService.listCustomers());
    }

    @GetMapping("/customers/customer")
    public ResponseEntity<CustomerDto> customerById(@RequestParam("id")int id){
        return ResponseEntity
                .ok()
                .body(customerService.findCustomerById(id));
    }
}
