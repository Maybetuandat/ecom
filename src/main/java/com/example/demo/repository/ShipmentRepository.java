package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {

}
