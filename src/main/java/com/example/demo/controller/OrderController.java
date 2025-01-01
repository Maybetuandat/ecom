package com.example.demo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.CartItem;
import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.Payment;
import com.example.demo.model.Shipment;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.OrderService;
import com.example.demo.service.PaymentService;
import com.example.demo.service.ShipmentService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ShipmentService shipmentService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    OrderDetailService orderDetailService;

    @PostMapping("/checkout/complete")
    public String createOrder( // process checkout and create order
            @RequestParam("paymentMethod") Integer paymentMethodId,
            @RequestParam("shippingMethod") Integer shippingMethodId,
            @RequestParam("totalAmount") Double totalAmount,

            HttpSession session,
            Model model) {
        if (session.getAttribute("customer") == null) {
            session.setAttribute("conditionMet", true);
            return "redirect:/";
        }
        Customer customer = (Customer) session.getAttribute("customer");

        Shipment shipment = shipmentService.getShipmentById(shippingMethodId);

        @SuppressWarnings("unchecked")
        Set<CartItem> selectedProducts = (Set<CartItem>) session.getAttribute("selectedProducts");

        Set<OrderDetail> orderDetails = new HashSet<>();
        for (CartItem cartItem : selectedProducts) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setPrice(cartItem.getSubTotal());
            orderDetail.setProduct(cartItem.getProduct());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetails.add(orderDetail);
        }
        Double totalPrice = totalAmount + shipment.getShippingCost();
        // session.removeAttribute("selectedProducts");
        orderService.createOrder(customer, orderDetails, totalPrice, paymentMethodId, shippingMethodId);
        System.out.println(selectedProducts);
        model.addAttribute("totalAmount", totalPrice);
        System.out.println(paymentMethodId);
        System.out.println(shippingMethodId);

        return "order_success";
    }

    @GetMapping("/order_history")
    public String viewHistoryOrder(HttpSession session, Model model) {
        if (session.getAttribute("customer") == null) {
            session.setAttribute("conditionMet", true);
            return "redirect:/";
        }

        Customer customer = (Customer) session.getAttribute("customer");
        List<Order> orders = orderService.getOrdersByCustomerId(customer.getId());
        // System.out.println(orders);
        model.addAttribute("orders", orders);
        return "order_history";

    }

    @GetMapping("/order_details/{id}")
    public String viewDetailOrder(@PathVariable int id, Model model) {
        Order order = orderService.getOrderById(id);
        System.out.println("order_details" + order);
        Payment payment = paymentService.getPaymentById(order.getPaymentId());
        System.out.println("order_details" + payment);
        Shipment shipment = shipmentService.getShipmentById(order.getShipmentId());
        System.out.println("order_details" + shipment);

        Set<OrderDetail> orderDetails = (Set<OrderDetail>) orderDetailService.getOrderDetailByOrderId(order.getId());
        System.out.println("order_details" + orderDetails);
        order.setOrderDetails(orderDetails);
        System.out.println(order.getOrderDetails());
        model.addAttribute("order", order);
        model.addAttribute("payment", payment);
        model.addAttribute("shipment", shipment);
        return "order_details";
    }

    @GetMapping("/admin/viewOrder")
    public String viewOrder(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "admin/viewOrder";
    }

    @GetMapping("/admin/order_details/{id}")
    public String viewDetailOrderAdmin(@PathVariable int id, Model model) {
        Order order = orderService.getOrderById(id);
        System.out.println("order_details" + order);
        Payment payment = paymentService.getPaymentById(order.getPaymentId());
        System.out.println("order_details" + payment);
        Shipment shipment = shipmentService.getShipmentById(order.getShipmentId());
        System.out.println("order_details" + shipment);

        Set<OrderDetail> orderDetails = (Set<OrderDetail>) orderDetailService.getOrderDetailByOrderId(order.getId());
        System.out.println("order_details" + orderDetails);
        order.setOrderDetails(orderDetails);
        System.out.println(order.getOrderDetails());
        model.addAttribute("order", order);
        model.addAttribute("payment", payment);
        model.addAttribute("shipment", shipment);
        return "admin/order_details";
    }

    @PostMapping("/admin/confirm_order/{id}")
    public String confirmOrder(@PathVariable int id, Model model) {
        Order order = orderService.getOrderById(id);
        order.setStatus("Success");
        orderService.updateOrder(order);
        return "redirect:/admin/viewOrder";

    }

}
