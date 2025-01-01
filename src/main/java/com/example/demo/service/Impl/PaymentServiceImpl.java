package com.example.demo.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Order;
import com.example.demo.model.Payment;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(String paymentMethod) {
        Payment payment = new Payment();
        payment.setPaymentMethod(paymentMethod);

        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(int id) {
        return paymentRepository.findById(id).get();
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public void savePayment(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(int id) {
        paymentRepository.deleteById(id);
    }

}
