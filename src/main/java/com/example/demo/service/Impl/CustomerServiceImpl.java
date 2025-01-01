package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CartItem;
import com.example.demo.model.Customer;
import com.example.demo.model.Item;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        // TODO Auto-generated method stub
        return customerRepository.findByEmail(email);
    }

    @Override
    public List<CartItem> findByCustomerId(int customerId) {
        return customerRepository.findById(customerId).get().getItems();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void updateCustomer(Customer customer) {
        // TODO Auto-generated method stub
        Customer customerToUpdate = customerRepository.findById(customer.getId()).get();
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setFullname(customer.getFullname());
        customerToUpdate.setPassword(customer.getPassword());
        customerToUpdate.setPhone(customer.getPhone());
        customerToUpdate.setAddress(customer.getAddress());
        customerToUpdate.setStatus(customer.getStatus());
        customerToUpdate.setUsername(customer.getUsername());
        customerRepository.save(customerToUpdate);

    }

    @Override
    public void deleteCustomer(int customerId) {
        // TODO Auto-generated method stub
        customerRepository.deleteById(customerId);
    }

    @Override
    public Customer getCustomerById(int customerId) {
        return customerRepository.findById(customerId).get();
    }

}
