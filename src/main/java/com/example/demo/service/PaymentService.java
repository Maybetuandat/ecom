package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Order;
import com.example.demo.model.Payment;

@Service
public interface PaymentService {

    public Payment createPayment(String paymentMethod);

    public Payment getPaymentById(int id);

    public List<Payment> getAllPayments();

    public void savePayment(Payment payment);

    public void deletePayment(int id);

}
