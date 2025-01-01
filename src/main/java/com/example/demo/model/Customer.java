package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 500)
    private String fullname;

    @Column(length = 500)
    private String address;

    @Column(length = 500)
    private String email;

    @Column(length = 500)
    private String phone;

    @Column(length = 500)
    private String username;

    @Column(length = 500)
    private String password;

    private int status;
    @Column(length = 500)
    private String role;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    public String toString() {
        return "Customer [id=" + id + ", fullname=" + fullname + ", address=" + address + ", email=" + email
                + ", phone="
                + phone + ", username=" + username + ", password=" + password + ", status=" + status
                + "]";
    }
}
