package com.example.demo.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;

@Service
public interface OrderService {

    public void createOrder(Customer customer, Set<OrderDetail> products, Double totalAmount, Integer shipmentId,
            Integer paymentId);

    public List<Order> getOrdersByCustomerId(int customerId);

    public Order getOrderById(int orderId);

    public List<Order> getAllOrders();

    public void updateOrder(Order order);
}
