package com.example.demo.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Clothes extends Item {
    private int sizeClothes;
    private String colorClothes;
    private String materialClothes;
    private String typeClothes;

    @Override
    public String toString() {
        return "Clothes{" +
                "sizeClothes=" + sizeClothes +
                ", colorClothes='" + colorClothes + '\'' +
                ", materialClothes='" + materialClothes + '\'' +
                ", typeClothes='" + typeClothes + '\'' +
                '}';
    }
}
