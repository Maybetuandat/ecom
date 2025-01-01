package com.example.demo.controller;

import com.example.demo.model.Staff;
import com.example.demo.service.CustomerService;
import com.example.demo.service.StaffService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String viewAllStaff(Model model, HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login";
        }
        Staff staff = (Staff) session.getAttribute("admin");

        if (staff.getRole().equals("admin") || staff.getRole().equals("manager")) {
            model.addAttribute("staffList", staffService.getAllStaff());
            return "/admin/staff/index";
        } else {
            session.setAttribute("errorMsg", "You do not have permission to access this page");
            return "redirect:/admin";
        }

    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("staff", new Staff());
        return "/admin/staff/create";
    }

    @PostMapping("/save")
    public String saveStaff(@ModelAttribute("staff") Staff staff) {
        staffService.saveStaff(staff);
        return "redirect:/admin/staff";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Staff staff = staffService.getStaffById(id);
        if (staff != null) {
            model.addAttribute("staff", staff);
            return "/admin/staff/edit";
        } else {
            return "redirect:/admin/staff";
        }
    }

    @PostMapping("/update/{id}")
    public String updateStaff(@PathVariable Integer id, @ModelAttribute("staff") Staff updatedStaff) {
        Staff existingStaff = staffService.getStaffById(id);
        if (existingStaff != null) {
            existingStaff.setFullname(updatedStaff.getFullname());
            existingStaff.setEmail(updatedStaff.getEmail());
            existingStaff.setPhoneNumber(updatedStaff.getPhoneNumber());
            existingStaff.setAddress(updatedStaff.getAddress());
            existingStaff.setDateOfBirth(updatedStaff.getDateOfBirth());
            existingStaff.setUsername(updatedStaff.getUsername());
            existingStaff.setPassword(updatedStaff.getPassword());
            existingStaff.setRole(updatedStaff.getRole());
            staffService.saveStaff(existingStaff);
        }
        return "redirect:/admin/staff";
    }

    @GetMapping("/delete/{id}")
    public String deleteStaff(@PathVariable Integer id) {
        staffService.deleteStaff(id);
        return "redirect:/admin/staff";
    }

}
