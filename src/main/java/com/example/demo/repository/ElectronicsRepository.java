package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Electronics;

public interface ElectronicsRepository extends JpaRepository<Electronics, Integer> {

}
