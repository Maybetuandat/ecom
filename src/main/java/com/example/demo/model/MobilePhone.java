package com.example.demo.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class MobilePhone extends Item {
    private String screenSizeMobilePhone;
    private String cameraMobilePhone;
    private String batteryMobilePhone;
    private String storageMobilePhone;
    private String ramMobilePhone;

    @Override
    public String toString() {
        return "MobilePhone{" +
                "screenSizeMobilePhone='" + screenSizeMobilePhone + '\'' +
                ", cameraMobilePhone='" + cameraMobilePhone + '\'' +
                ", batteryMobilePhone='" + batteryMobilePhone + '\'' +
                ", storageMobilePhone='" + storageMobilePhone + '\'' +
                ", ramMobilePhone='" + ramMobilePhone + '\'' +
                '}';
    }
}
