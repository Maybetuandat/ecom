package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shipments") // Tên bảng là "shipments"
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String shippingMethod; // Phương thức giao hàng (ví dụ: "Standard", "Express", "Overnight")

    @Column(nullable = false)
    private Double shippingCost; // Giá tiền cho phương thức giao hàng

    @Column(nullable = false)
    private int deliveryDays; // Số ngày giao hàng

    @Override
    public String toString() {
        return "Shipment [id=" + id + ", shippingMethod=" + shippingMethod +
                ", shippingCost=" + shippingCost + ", deliveryDays=" + deliveryDays + "]";
    }
}
