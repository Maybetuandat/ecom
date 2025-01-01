package com.example.demo.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Laptop extends Item {
    private String processorLaptop;
    private String ramLaptop;
    private String storageLaptop;
    private String graphicsLaptop;
    private String osLaptop;
    private String displayLaptop;
    private String batteryLaptop;

    @Override
    public String toString() {

        return "Laptop{" +
                "processorLaptop='" + processorLaptop + '\'' +
                ", ramLaptop='" + ramLaptop + '\'' +
                ", storageLaptop='" + storageLaptop + '\'' +
                ", graphicsLaptop='" + graphicsLaptop + '\'' +
                ", osLaptop='" + osLaptop + '\'' +
                ", displayLaptop='" + displayLaptop + '\'' +
                ", batteryLaptop='" + batteryLaptop + '\'' +
                '}';
    }
}
