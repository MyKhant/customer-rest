package com.example.customerrest.service;

import com.example.customerrest.dao.CustomerDao;
import com.example.customerrest.ds.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public void saveCustomer(Customer customer){
        customerDao.save(customer);
    }

    public Iterable<Customer> listCustomers(){
        return customerDao.findAll();
    }
}
