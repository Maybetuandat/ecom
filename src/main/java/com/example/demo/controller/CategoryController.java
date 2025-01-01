package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Category;
import com.example.demo.model.Customer;
import com.example.demo.model.Item;
import com.example.demo.service.CategoryService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ItemService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ItemService productService;

    @Autowired
    CustomerService customerService;

    @GetMapping({ "/", "" })
    public String index() {

        return "admin/index";
    }

    @GetMapping("/category")
    public String category(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/category";
    }

    @PostMapping("/saveCategory")
    public String saveCategory(@ModelAttribute Category category, @RequestParam("file") MultipartFile file,
            HttpSession session) throws IOException {

        String imageName = file != null ? file.getOriginalFilename() : "default.png";
        Boolean existCategory = categoryService.exsitCategory(category.getName());
        category.setImageName(imageName);
        if (existCategory) {
            session.setAttribute("errorMsg", "Category already exists");
        } else {
            Category savCategory = categoryService.saveCategory(category);
            if (ObjectUtils.isEmpty(savCategory)) {
                session.setAttribute("errorMsg", "Failed to save category");
            } else {

                File saveFile = new ClassPathResource("static/img").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "category_img" + File.separator
                        + file.getOriginalFilename());

                System.out.println(path);

                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                session.setAttribute("successMsg", "Category saved successfully");
            }
        }

        return "redirect:/admin/category";
    }

    @GetMapping("/deleteCategory/{id}")
    public String deleteCategoryById(@PathVariable int id, HttpSession session) {
        Boolean isDeleted = categoryService.deleteCategory(id);
        if (isDeleted) {
            session.setAttribute("categoryDetailSuccess", "Delete successfully");
        } else {
            session.setAttribute("categoryDetailError", "Failed to delete category");
        }
        return "redirect:/admin/category";
    }

    @GetMapping("/viewEditCategory/{id}")
    public String viewEditCategory(@PathVariable int id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        model.addAttribute("id", id);
        return "admin/edit_category";
    }

    @PostMapping("/updateCategory/{id}")
    public String updateCategory(@PathVariable int id, @ModelAttribute Category category,
            @RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
        if (file.isEmpty()) {
            Category category2 = categoryService.getCategoryById(id);
            category.setImageName(category2.getImageName());
        } else {
            category.setImageName(file.getOriginalFilename());
        }
        Boolean kt = false;
        try {
            kt = categoryService.updateCategory(category);
        } catch (Exception e) {
            // TODO: handle exception
        }
        if (kt) {
            if (!file.isEmpty()) {
                File saveFile = new ClassPathResource("static/img").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "category_img" + File.separator
                        + file.getOriginalFilename());
                System.out.println(path);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }
            session.setAttribute("categoryDetailSuccess", "Update successfully");
        } else {
            session.setAttribute("categoryDetailError", "Failed to update category");
        }
        return "redirect:/admin/category";

    }

}
