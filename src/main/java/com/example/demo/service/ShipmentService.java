package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Shipment;

@Service
public interface ShipmentService {
    public void createShipment(String shippingMethod, Double shippingCost, int deliveryDays);

    public List<Shipment> getAllShipments();

    public Shipment getShipmentById(int id);
}
