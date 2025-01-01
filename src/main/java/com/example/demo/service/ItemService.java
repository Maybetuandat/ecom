package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Item;

public interface ItemService {

    public List<Item> getAllItems();

    public Item getItemById(int id);

    public Item saveOrUpdateItem(Item item);

    public void deleteItem(int id);

    public Item updateItem(Item item, MultipartFile image);

}
