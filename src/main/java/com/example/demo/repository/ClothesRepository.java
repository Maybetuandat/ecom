package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Clothes;

public interface ClothesRepository extends JpaRepository<Clothes, Integer> {

}
