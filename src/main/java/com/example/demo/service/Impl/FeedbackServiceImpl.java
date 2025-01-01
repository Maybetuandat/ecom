package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Feedback;
import com.example.demo.repository.FeedbackRepository;
import com.example.demo.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackRepository commentRepositorty;

    @Override
    public Feedback saveComment(Feedback comment) {
        return commentRepositorty.save(comment);
    }

    @Override
    public List<Feedback> getCommentByProductId(int productId) {
        return commentRepositorty.findByProductId(productId);
    }

}
