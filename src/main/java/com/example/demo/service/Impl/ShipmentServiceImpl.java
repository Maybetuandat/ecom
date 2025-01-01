package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Order;
import com.example.demo.model.Shipment;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.service.ShipmentService;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Override
    public void createShipment(String shippingMethod, Double shippingCost, int deliveryDays) {

        Shipment shipment = new Shipment();
        shipment.setShippingMethod(shippingMethod);
        shipment.setShippingCost(shippingCost);
        shipment.setDeliveryDays(deliveryDays);
        shipmentRepository.save(shipment);
    }

    @Override
    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    @Override
    public Shipment getShipmentById(int id) {
        return shipmentRepository.findById(id).get();
    }

}
