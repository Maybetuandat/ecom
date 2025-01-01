package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Item;
import com.example.demo.service.ItemService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/items")
public class apiItem {

    @Autowired
    private ItemService itemService;

    // Lấy danh sách tất cả sản phẩm
    @GetMapping
    public List<Item> getAllItems() {

        List<Item> items = itemService.getAllItems();
        List<String> fieldNames = new ArrayList<>();
        // for (Item item : items) {
        // // Sử dụng reflection để lấy tên các thuộc tính của item
        // Field[] fields = item.getClass().getDeclaredFields();
        // for (Field field : fields) {
        // fieldNames.add(field.getName());
        // }
        // }
        for (Item item : items) {
            System.out.println(item.getName());
        }
        return items;
    }

    // Lấy một sản phẩm cụ thể theo ID
    // @GetMapping("/{id}")
    // public ResponseEntity<Item> getItemById(@PathVariable int id) {
    // return itemService.getItemById(id)
    // .map(ResponseEntity::ok)
    // .orElse(ResponseEntity.notFound().build());
    // }

    // // Thêm mới hoặc cập nhật sản phẩm
    @PostMapping
    public Item createOrUpdateItem(@RequestBody Item item, HttpSession session) {
        item.setDiscountPrice(item.getPrice() - item.getPrice() * item.getDiscount() / 100);
        session.setAttribute("successMsg", "Sucessfully added item");
        System.out.println(item);
        return itemService.saveOrUpdateItem(item);
    }

    // Xóa sản phẩm theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable int id) {
        try {
            itemService.deleteItem(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
