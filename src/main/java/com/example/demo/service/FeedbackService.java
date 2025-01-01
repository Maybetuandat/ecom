package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Feedback;

@Service
public interface FeedbackService {
    public Feedback saveComment(Feedback comment);

    public List<Feedback> getCommentByProductId(int productId);
}
