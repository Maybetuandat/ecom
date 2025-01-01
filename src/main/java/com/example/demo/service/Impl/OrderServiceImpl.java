package com.example.demo.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;

import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public void createOrder(Customer customer, Set<OrderDetail> products, Double totalAmount, Integer shipmentId,
            Integer paymentId) {

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDetails(products);
        order.setTotalAmount(totalAmount);
        order.setStatus("Pending");
        order.setOrderDate(new Date());
        order.setShipmentId(shipmentId);
        order.setPaymentId(paymentId);

        orderRepository.save(order);
        for (OrderDetail orderDetail : products) {
            orderDetail.setOrder(order);
            orderDetailRepository.save(orderDetail);
        }

    }

    @Override
    public List<Order> getOrdersByCustomerId(int customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public Order getOrderById(int orderId) {
        return orderRepository.findById(orderId).get();

    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

}
