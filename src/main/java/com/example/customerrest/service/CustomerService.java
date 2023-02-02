package com.example.customerrest.service;

import com.example.customerrest.dao.CustomerDao;
import com.example.customerrest.ds.Customer;
import com.example.customerrest.ds.CustomerDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public CustomerDto saveCustomer(CustomerDto customerDto){
        Customer customer = customerDao.save(toEntity(customerDto));
        return toDto(customer);
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

    public void deleteCustomer(int id){
        if (customerDao.existsById(id)){
            customerDao.deleteById(id);
        }
        else {
            throw new EntityNotFoundException(id + " Not Found!");
        }
    }

    @Transactional
    public CustomerDto updateCustomer(CustomerDto customerDto){
        Customer exitingCustomer = customerDao.findById(customerDto.id())
                .orElseThrow(EntityNotFoundException::new);

        exitingCustomer.setCode(customerDto.code());
        exitingCustomer.setId(customerDto.id());
        exitingCustomer.setFirstName(customerDto.firstName());
        exitingCustomer.setLastName(customerDto.lastName());
        exitingCustomer.setEmail(customerDto.email());

        return customerDto;
    }

    public CustomerDto changeCode(int id, String code){
        Customer customer = customerDao.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        customer.setCode(code);
        return toDto(customer);
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
