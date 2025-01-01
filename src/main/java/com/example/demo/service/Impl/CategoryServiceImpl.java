package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

      @Autowired
      private CategoryRepository categoryRepository;

      @Override
      public Category saveCategory(Category category) {
            return categoryRepository.save(category);
      }

      @Override
      public List<Category> getAllCategories() {
            return categoryRepository.findAll();
      }

      @Override
      public Boolean exsitCategory(String name) {
            return categoryRepository.existsByName(name);
      }

      @Override
      public Boolean deleteCategory(int id) {

            Boolean isDeleted = false;
            try {
                  categoryRepository.deleteById(id);
                  isDeleted = true;
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return isDeleted;
      }

      @Override
      public Category getCategoryById(int id) {
            Category category = null;
            try {
                  category = categoryRepository.findById(id).get();
                  return category;
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return category;
      }

      @Override
      public Boolean updateCategory(Category category) {
            try {
                  // Kiểm tra xem bản ghi có tồn tại hay không
                  if (!categoryRepository.existsById(category.getId())) {
                        throw new IllegalArgumentException("Category with ID " + category.getId() + " does not exist.");
                  }
                  // Cập nhật bản ghi
                  categoryRepository.save(category);
                  return true;
            } catch (Exception e) {
                  // Ghi lại lỗi để kiểm tra
                  e.printStackTrace();
                  return false;
            }
      }

}
