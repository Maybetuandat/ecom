package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.model.Payment;
import com.example.demo.model.Staff;
import com.example.demo.service.PaymentService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

@RequestMapping("/admin/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Danh sách phương thức thanh toán
    @GetMapping
    public String index(Model model, HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login";
        }
        Staff staff = (Staff) session.getAttribute("admin");
        if (staff.getRole().equals("admin")) {
            List<Payment> payments = paymentService.getAllPayments();
            model.addAttribute("payments", payments);
            return "admin/index_payment";
        } else {
            session.setAttribute("errorMsg", "You do not have permission to access this page");
            return "redirect:/admin";
        }

    }

    // Form thêm mới
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("payment", new Payment());
        return "admin/create_payment";
    }

    // Xử lý thêm mới
    @PostMapping("/create")
    public String store(@ModelAttribute("payment") Payment payment) {
        paymentService.savePayment(payment);
        return "redirect:/admin/payment";
    }

    // Form chỉnh sửa
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Payment payment = paymentService.getPaymentById(id);
        model.addAttribute("payment", payment);
        return "admin/edit_payment";
    }

    // Xử lý cập nhật
    @PostMapping("/edit")
    public String update(@ModelAttribute("payment") Payment payment) {
        paymentService.savePayment(payment);
        return "redirect:/admin/payment";
    }

    // Xóa
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        paymentService.deletePayment(id);
        return "redirect:/admin/payment";
    }

}
