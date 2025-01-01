package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Book;
import com.example.demo.model.CartItem;
import com.example.demo.model.Category;
import com.example.demo.model.Customer;
import com.example.demo.model.Item;
import com.example.demo.model.Staff;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.ElectronicsRepository;
import com.example.demo.repository.LaptopRepository;
import com.example.demo.repository.MobilePhoneRepository;
import com.example.demo.repository.ShoesRepository;
import com.example.demo.model.Feedback;
import com.example.demo.service.BookService;
import com.example.demo.service.CartItemService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ItemService;
import com.example.demo.service.FeedbackService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ItemController {

    @Autowired
    ItemService itemService;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    FeedbackService feedbackService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    BookService bookService;
    @Autowired
    LaptopRepository laptopRepository;

    @Autowired
    ShoesRepository shoesRepository;

    @Autowired
    MobilePhoneRepository mobilePhoneRepository;

    @Autowired
    ElectronicsRepository electronicsRepository;

    // @SuppressWarnings("unchecked")
    // @GetMapping("/")
    // public String index(Model model, HttpSession session, @RequestParam(name =
    // "search", required = false) String searchKeyword) {

    // Boolean isConditionMet = (Boolean) session.getAttribute("conditionMet");
    // model.addAttribute("showDialog", isConditionMet);
    // List<Item> products = itemService.getAllItems();
    // model.addAttribute("products", products);
    // System.out.println(products);

    // if (session.getAttribute("customer") != null) {
    // // Lấy danh sách các CartItem từ dịch vụ
    // Object cartItemsObj = cartItemService
    // .getAllCartItems(((Customer) session.getAttribute("customer")).getId());

    // // Kiểm tra nếu đối tượng trả về là loại PersistentBag (hoặc List)
    // Set<CartItem> cartItems = new HashSet<>();
    // if (cartItemsObj instanceof Set<?>) {
    // cartItems = (Set<CartItem>) cartItemsObj;
    // } else if (cartItemsObj instanceof List<?>) {
    // cartItems.addAll((List<CartItem>) cartItemsObj);
    // }

    // // Thêm vào model số lượng item trong giỏ hàng
    // model.addAttribute("numberOfItemInCart", cartItems.size());
    // }

    // // System.out.println(products);
    // return "index";

    // }

    @SuppressWarnings("unchecked")
    @GetMapping("/")
    public String index(Model model, HttpSession session,
            @RequestParam(name = "search", required = false) String searchKeyword) {

        // Hiển thị hộp thoại (nếu có)
        Boolean isConditionMet = (Boolean) session.getAttribute("conditionMet");
        model.addAttribute("showDialog", isConditionMet);

        // Lấy danh sách tất cả sản phẩm
        List<Item> products = itemService.getAllItems();

        // Lọc danh sách sản phẩm nếu có từ khóa tìm kiếm
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            products = products.stream()
                    .filter(item -> item.getName().toLowerCase().contains(searchKeyword.toLowerCase()))
                    .toList();
            model.addAttribute("searchKeyword", searchKeyword); // Truyền từ khóa vào model để hiển thị lại trên form
        }

        // Thêm sản phẩm vào model
        model.addAttribute("products", products);

        // Kiểm tra giỏ hàng nếu có thông tin khách hàng trong session
        if (session.getAttribute("customer") != null) {
            Object cartItemsObj = cartItemService
                    .getAllCartItems(((Customer) session.getAttribute("customer")).getId());

            // Kiểm tra kiểu dữ liệu của cartItemsObj
            Set<CartItem> cartItems = new HashSet<>();
            if (cartItemsObj instanceof Set<?>) {
                cartItems = (Set<CartItem>) cartItemsObj;
            } else if (cartItemsObj instanceof List<?>) {
                cartItems.addAll((List<CartItem>) cartItemsObj);
            }

            // Thêm số lượng sản phẩm trong giỏ hàng vào model
            model.addAttribute("numberOfItemInCart", cartItems.size());
        }

        return "index";
    }

    @GetMapping("/products/{id}")
    public String viewDetailProduct(@PathVariable int id, Model model) {
        Item product = itemService.getItemById(id);
        List<Feedback> feedbacks = feedbackService.getCommentByProductId(id);
        // for (Review comment : comments) {
        // System.out.println("nguu " + comment.getCustomer());
        // }

        Double rating = feedbacks.stream().mapToDouble(Feedback::getRating).average().orElse(0.0);
        product.setRating(rating);

        model.addAttribute("product", product);
        model.addAttribute("comments", feedbacks);
        System.out.println(feedbacks);
        return "detail_product";
    }

    // @PostMapping("/admin/updateProduct")
    // public String updateProduct(@ModelAttribute Item product,
    // @RequestParam("file") MultipartFile image,
    // HttpSession session, Model m) {
    // System.out.println(product);
    // // if (product.getCategory().equals("Book")) {
    // // Book book = (Book) product;
    // // System.out.println("Co vao day");
    // // bookService.updateBook(book, image);

    // // }

    // Item item = new Item();
    // item.setId(product.getId());
    // item.setName(product.getName());
    // item.setPrice(product.getPrice());
    // item.setCategory(product.getCategory());
    // item.setBrand(product.getBrand());
    // item.setModel(product.getModel());
    // item.setDescription(product.getDescription());
    // item.setStock(product.getStock());
    // item.setDiscount(product.getDiscount());
    // item.setDiscountPrice(product.getDiscountPrice());
    // item.setDate(product.getDate());
    // item.setRating(product.getRating());

    // Item updateProduct = itemService.updateItem(item, image);
    // // if (!ObjectUtils.isEmpty(updateProduct)) {
    // // session.setAttribute("successMsg", "Product update success");
    // // } else {
    // // session.setAttribute("errorMsg", "Something wrong on server");
    // // }

    // return "redirect:/admin/loadViewProduct";
    // }

    @GetMapping("/admin/loadAddProduct")
    public String loadAddProduct(HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login";
        }
        Staff staff = (Staff) session.getAttribute("admin");
        if (staff.getRole().equals("admin") || staff.getRole().equals("manager")) {
            return "admin/add_product";
        } else {
            session.setAttribute("errorMsg", "You do not have permission to access this page");
            return "redirect:/admin";
        }

    }

    @GetMapping("/admin/loadViewProduct")
    public String loadViewProduct(Model model, HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login";
        }
        Staff staff = (Staff) session.getAttribute("admin");

        if (staff.getRole().equals("admin") || staff.getRole().equals("manager")
                || staff.getRole().equals("warehouse")) {
            List<Item> products = itemService.getAllItems();
            // System.out.println(products);
            model.addAttribute("products", products);
            return "admin/view_product";
        } else {
            session.setAttribute("errorMsg", "You do not have permission to access this page");
            return "redirect:/admin";
        }

    }

    @GetMapping("/admin/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id, HttpSession session) {
        itemService.deleteItem(id);
        session.setAttribute("successMsg", "Product deleted success");
        return "redirect:/admin/loadViewProduct";
    }

    @GetMapping("/admin/editProduct/{id}")
    public String EditProduct(@PathVariable int id, Model model) {
        Item product = itemService.getItemById(id);
        List<Category> category = categoryService.getAllCategories();
        model.addAttribute("categories", category);
        model.addAttribute("product", product);
        return "admin/edit_product";
    }

}
