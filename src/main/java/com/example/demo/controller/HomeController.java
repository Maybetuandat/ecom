package com.example.demo.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.model.CartItem;
import com.example.demo.model.Category;
import com.example.demo.model.Feedback;
import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.Payment;
import com.example.demo.model.Item;
import com.example.demo.model.Shipment;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.CartItemService;
import com.example.demo.service.FeedbackService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.OrderService;
import com.example.demo.service.PaymentService;
import com.example.demo.service.ItemService;
import com.example.demo.service.ShipmentService;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class HomeController {

    @Autowired
    ShipmentService shipmentService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    CartItemService cartItemService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/checkout")
    public String viewCheckOut(@RequestParam("selectedItems") List<Integer> selectedItemIds,
            HttpSession session, Model model) {
        // Lấy danh sách Product từ cơ sở dữ liệu
        if (session.getAttribute("customer") == null) {
            session.setAttribute("conditionMet", true);
            return "redirect:/";
        }

        Set<CartItem> selectedProducts = cartItemService.getAllCartItemsByIds(selectedItemIds);

        // System.out.println(selectedProducts);
        // System.out.println(selectedItemIds);
        // Tính tổng tiền

        Double totalAmount = selectedProducts.stream().mapToDouble(CartItem::getSubTotal).sum();
        System.out.println(totalAmount);

        model.addAttribute("selectedProducts", selectedProducts);
        session.setAttribute("selectedProducts", selectedProducts);
        model.addAttribute("totalAmount", totalAmount);
        List<Shipment> shipment = shipmentService.getAllShipments();
        model.addAttribute("shipments", shipment);

        List<Payment> payment = paymentService.getAllPayments();
        model.addAttribute("payments", payment);

        return "checkout";

    }

}
