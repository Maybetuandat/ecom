package com.example.demo.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.model.OrderDetail;

@Service
public interface OrderDetailService {
    public Set<OrderDetail> getOrderDetailByOrderId(int orderId);
}
