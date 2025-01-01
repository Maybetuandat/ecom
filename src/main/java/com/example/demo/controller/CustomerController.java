package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Customer;
import com.example.demo.model.Staff;
import com.example.demo.service.CustomerService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/checkLogin")
    public String checkLoginCustomer(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Customer customer = customerService.getCustomerByEmail(email);
        if (customer == null) {
            session.setAttribute("errorMsg", "User is not exist");
            return "redirect:/login";
        } else {
            if (customer.getPassword().equals(password)) {
                session.setAttribute("customer", customer);
                session.setAttribute("conditionMet", false);
                return "redirect:/";
            } else {
                session.setAttribute("errorMsg", "User is not exist");
                return "redirect:/login";
            }
        }
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute Customer customer, HttpSession session) {
        if (customerService.existsByEmail(customer.getEmail())) {
            session.setAttribute("errorMsg", "Email already exists");
            return "redirect:/register";
        } else {
            customerService.addCustomer(customer);
            session.setAttribute("customer", customer);
            session.setAttribute("succMsg", "Register successfully");
            return "redirect:/register";
        }
    }

    @GetMapping("/account")
    public String account(HttpSession session, Model model) {
        if (session.getAttribute("customer") == null) {
            session.setAttribute("conditionMet", true);
            return "redirect:/";
        }
        Customer customer = (Customer) session.getAttribute("customer");
        model.addAttribute("customer", customer);
        return "account";
    }

    @GetMapping("/admin/customer")
    public String listCustomers(Model model, HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login";
        }
        Staff staff = (Staff) session.getAttribute("admin");

        if (staff.getRole().equals("admin")) {
            model.addAttribute("customerList", customerService.getAllCustomers());
            return "admin/customer/list";
        } else {
            session.setAttribute("errorMsg", "You do not have permission to access this page");
            return "redirect:/admin";
        }

    }

    @GetMapping("/admin/customer/delete/{id}")
    public String deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return "redirect:/admin/customer";
    }

    @GetMapping("/admin/customer/edit/{id}")
    public String editCustomer(@PathVariable int id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "admin/customer/edit";
    }

    @PostMapping("/admin/customer/update/{id}")
    public String updateCustomer(@ModelAttribute Customer customer) {
        customerService.updateCustomer(customer);
        return "redirect:/admin/customer";
    }

    @PostMapping("/admin/customer/save")
    public String saveCustomer(@ModelAttribute Customer customer) {
        customerService.addCustomer(customer);
        return "redirect:/admin/customer";
    }

    @GetMapping("/admin/customer/create")
    public String createCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "admin/customer/create";
    }

}
