package com.example.customerrest.service;

import com.example.customerrest.dao.CustomerDao;
import com.example.customerrest.ds.Customer;
import com.example.customerrest.ds.CustomerDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;



    public void saveCustomer(CustomerDto customerDto){

        customerDao.save(toEntity(customerDto));
    }

    public Customer toEntity(CustomerDto customerDto){
        return new Customer(
                customerDto.code(),
                customerDto.firstName(),
                customerDto.lastName(),
                customerDto.email()
        );
    }
    public CustomerDto toDto(Customer customer){
        return new CustomerDto(
                customer.getId(),
                customer.getCode(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail()
        );
    }
    public CustomerDto findCustomerById(int id){
        Customer customer = customerDao.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return toDto(customer);
    }

    public List<CustomerDto> listCustomers(){
        List<CustomerDto> customers = new ArrayList<>();
        for (Customer customer:customerDao.findAll()){
            customers.add(toDto(customer));
        }
        return customers;
    }
}
