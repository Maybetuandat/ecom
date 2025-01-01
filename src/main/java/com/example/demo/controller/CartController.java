package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.CartItem;
import com.example.demo.model.Customer;
import com.example.demo.service.CartItemService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

    @Autowired
    CartItemService cartItemService;

    @GetMapping("/viewCart")
    public String viewCart(HttpSession session, Model model) {
        if (session.getAttribute("customer") == null) {
            session.setAttribute("conditionMet", true);
            return "redirect:/";
        } else {

            List<CartItem> cartItems = cartItemService
                    .getAllCartItems((((Customer) session.getAttribute("customer")).getId()));
            System.out.println(cartItems);
            model.addAttribute("cart", cartItems);
            return "view_cart";
        }

    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable("id") int productId, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        System.out.println(customer);
        System.out.println(productId);
        if (customer != null) {
            cartItemService.addProductToCart(productId, customer.getId());
            session.setAttribute("successMsg", true);
            System.out.println("Add to cart successfully");
        } else {
            session.setAttribute("conditionMet", true);
            return "redirect:/";
        }
        return "redirect:/";
    }

    @GetMapping("/deleteProductInCart/{id}")
    public String deleteProductInCart(@PathVariable("id") int cartItemId, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            cartItemService.removeProductFromCart(cartItemId);
            session.setAttribute("successMsg", "Product removed from cart successfully");
        } else {
            session.setAttribute("conditionMet", true);
            return "redirect:/";
        }
        return "redirect:/viewCart";
    }

}
