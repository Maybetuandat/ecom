package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    public List<Feedback> findByProductId(int productId);
}
