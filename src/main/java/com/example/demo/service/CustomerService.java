package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.CartItem;
import com.example.demo.model.Customer;
import com.example.demo.model.Item;

@Service
public interface CustomerService {

    public Boolean existsByEmail(String email);

    public Customer addCustomer(Customer customer);

    public Customer getCustomerByEmail(String email);

    public List<CartItem> findByCustomerId(int customerId);

    public List<Customer> getAllCustomers();

    public void updateCustomer(Customer customer);

    public void deleteCustomer(int customerId);

    public Customer getCustomerById(int customerId);
}
