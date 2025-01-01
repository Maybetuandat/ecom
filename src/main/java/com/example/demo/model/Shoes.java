package com.example.demo.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Shoes extends Item {
    private String sizeShoes;
    private String colorShoes;
    private String materialShoes;
    private String typeShoes;

    @Override
    public String toString() {
        return "Shoes{" +
                "sizeShoes='" + sizeShoes + '\'' +
                ", colorShoes='" + colorShoes + '\'' +
                ", materialShoes='" + materialShoes + '\'' +
                ", typeShoes='" + typeShoes + '\'' +
                '}';
    }
}
