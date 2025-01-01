package com.example.demo.service.Impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CartItem;
import com.example.demo.model.Customer;
import com.example.demo.model.Item;
import com.example.demo.repository.CartItemRepository;

import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.CartItemService;

import jakarta.transaction.Transactional;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ItemRepository productRepository;

    @Override
    public void addProductToCart(int productId, int customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        List<CartItem> cartItems = customer.getItems();
        Item product = productRepository.findById(productId).get();
        boolean isExist = false;
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getId() == productId) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItem.calculateSubTotal();
                cartItemRepository.save(cartItem);
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cartItem.setCustomer(customerRepository.findById(customerId).get());
            cartItem.calculateSubTotal();
            cartItemRepository.save(cartItem);
        }
        customerRepository.save(customer);

    }

    @Override
    public List<CartItem> getAllCartItems(int customerId) {
        return customerRepository.findById(customerId).get().getItems();
    }

    @Override
    public Set<CartItem> getAllCartItemsByIds(List<Integer> cartItemIds) {
        return new HashSet<>(cartItemRepository.findAllById(cartItemIds));
    }

    @Override
    @Transactional
    public void removeProductFromCart(int cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

}
