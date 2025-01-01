package com.example.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Customer;
import com.example.demo.model.Feedback;
import com.example.demo.model.Item;
import com.example.demo.service.FeedbackService;
import com.example.demo.service.ItemService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;
    @Autowired
    ItemService productService;

    @GetMapping("/review_product/{id}")
    public String addCommentAndRaString(@PathVariable int id, Model model, HttpSession session) {
        // Item product = productService.getItemById(id);
        // session.setAttribute("product", product);
        // model.addAttribute("product", product);
        return "review_product";
    }

    @PostMapping("/review")
    public String submitFeedback(
            @RequestParam String comment,
            @RequestParam int rating,
            HttpSession session) {
        Item product = (Item) session.getAttribute("product");
        session.removeAttribute("product");
        Customer customer = (Customer) session.getAttribute("customer");
        Feedback newReview = new Feedback();
        newReview.setComment(comment);
        newReview.setProduct(product);
        newReview.setCustomer(customer);
        newReview.setTimeCreated(new Date());
        newReview.setRating(rating);
        feedbackService.saveComment(newReview);
        session.setAttribute("successMsg", "Review submitted successfully");
        return "redirect:/";

    }
}
