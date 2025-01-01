package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Chia các bảng theo chiến lược JOINED

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, // Sử dụng tên loại để phân biệt
                include = JsonTypeInfo.As.PROPERTY, // Thêm thông tin loại trong JSON
                property = "type" // Tên trường để phân biệt loại
)
@JsonSubTypes({
                @JsonSubTypes.Type(value = Laptop.class, name = "Laptop"),
                @JsonSubTypes.Type(value = Book.class, name = "Book"),
                @JsonSubTypes.Type(value = MobilePhone.class, name = "MobilePhone"),
                @JsonSubTypes.Type(value = Electronics.class, name = "Electronics"),
                @JsonSubTypes.Type(value = Clothes.class, name = "Clothes"),
                @JsonSubTypes.Type(value = Shoes.class, name = "Shoes"),
})
public abstract class Item {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(length = 500)
        private String name;

        @Column(length = 3000)
        private String description;

        private String brand;
        private String model;
        private Date date; // this is release date

        private String category;
        private Double price;
        private int stock;
        private String imageUrl;
        private int discount;
        private Double discountPrice;
        private Double rating = 0.0;

        @Override
        public String toString() {
                return "Item{" +
                                "id=" + id +
                                ", name='" + name + '\'' +
                                ", description='" + description + '\'' +
                                ", brand='" + brand + '\'' +
                                ", model='" + model + '\'' +
                                ", date=" + date +
                                ", category='" + category + '\'' +
                                ", price=" + price +
                                ", stock=" + stock +
                                ", imageUrl='" + imageUrl + '\'' +
                                ", discount=" + discount +
                                ", discountPrice=" + discountPrice +
                                ", rating=" + rating +
                                '}';
        }
}
