package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Category;

public interface CategoryService {

    public Category saveCategory(Category category);

    public Boolean exsitCategory(String name);

    public List<Category> getAllCategories();

    public Boolean deleteCategory(int id);

    public Category getCategoryById(int id);

    public Boolean updateCategory(Category category);
}
