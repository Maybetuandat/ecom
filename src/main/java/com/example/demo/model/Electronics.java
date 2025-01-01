package com.example.demo.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Electronics extends Item {
    private String powerConsumption;
    private String productType;
    private String capacity;
    private String controlType;

    @Override
    public String toString() {
        return "Electronics{" +
                "powerConsumption='" + powerConsumption + '\'' +
                ", productType='" + productType + '\'' +
                ", capacity='" + capacity + '\'' +
                ", controlType='" + controlType + '\'' +
                '}';
    }
}
