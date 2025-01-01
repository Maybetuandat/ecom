package com.example.demo.controller;

import com.example.demo.model.Shipment;
import com.example.demo.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    // Danh sách shipment
    @GetMapping
    public String listShipments(Model model) {
        model.addAttribute("shipments", shipmentService.getAllShipments());
        return "admin/shipment/list";
    }

    // Form tạo shipment
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("shipment", new Shipment());
        return "admin/shipment/create";
    }

    // Lưu shipment mới
    @PostMapping("/create")
    public String createShipment(@ModelAttribute Shipment shipment) {
        shipmentService.saveShipment(shipment);
        return "redirect:/admin/shipments";
    }

    // Form chỉnh sửa shipment
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("shipment", shipmentService.getShipmentById(id));
        return "admin/shipment/edit";
    }

    // Cập nhật shipment
    @PostMapping("/edit/{id}")
    public String updateShipment(@PathVariable int id, @ModelAttribute Shipment shipment) {
        shipment.setId(id);
        shipmentService.saveShipment(shipment);
        return "redirect:/admin/shipments";
    }

    // Xóa shipment
    @GetMapping("/delete/{id}")
    public String deleteShipment(@PathVariable int id) {
        shipmentService.deleteShipment(id);
        return "redirect:/admin/shipments";
    }
}
