package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.MobilePhone;

public interface MobilePhoneRepository extends JpaRepository<MobilePhone, Integer> {

}
