package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Customer;
import com.example.demo.model.Staff;
import com.example.demo.service.StaffService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    StaffService staffService;

    @GetMapping("/admin/login")
    public String login() {
        return "admin/login";
    }

    @PostMapping("/admin/checkLogin")
    public String checkLoginCustomer(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Staff customer = staffService.getStaffByEmail(email);
        if (customer == null) {
            session.setAttribute("errorMsg", "User is not exist");
            return "redirect:/admin/login";
        } else {
            if (customer.getPassword().equals(password)) {
                session.setAttribute("admin", customer);
                session.setAttribute("conditionMet", false);
                return "redirect:/admin";
            } else {
                session.setAttribute("errorMsg", "User is not exist");
                return "redirect:/admin/login";
            }
        }
    }

}
