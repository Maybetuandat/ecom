package com.example.demo.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.model.CartItem;

@Service
public interface CartItemService {

    public void addProductToCart(int productId, int customerId);

    public List<CartItem> getAllCartItems(int customerId);

    public Set<CartItem> getAllCartItemsByIds(List<Integer> cartItemIds);

    public void removeProductFromCart(int cartItemId);
}
