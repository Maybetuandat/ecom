package com.example.demo.service.Impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // Lấy một sản phẩm theo ID
    public Item getItemById(int id) {
        return itemRepository.findById(id).orElse(null);
    }

    // Thêm mới hoặc cập nhật sản phẩm
    public Item saveOrUpdateItem(Item item) {
        return itemRepository.save(item);
    }

    // Xóa sản phẩm theo ID
    public void deleteItem(int id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
        } else {
            throw new RuntimeException("Item not found with id: " + id);
        }
    }

    @Override
    public Item updateItem(Item item, MultipartFile image) {
        // Kiểm tra xem sản phẩm đã tồn tại trong cơ sở dữ liệu chưa
        System.out.println("ItemServiceImpl: " + item);
        return itemRepository.save(item);
    }
}
