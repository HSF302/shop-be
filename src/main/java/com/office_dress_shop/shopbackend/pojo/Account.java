package com.office_dress_shop.shopbackend.pojo;

import com.office_dress_shop.shopbackend.enums.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "Accounts")
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "Email", unique = true, nullable = false)
    public String email;

    @Column(name = "Password", nullable = false)
    public String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "Role", nullable = false)
    public Role role;

    @Column(name = "Name", nullable = false)
    public String name;

    @Column(name = "Address", nullable = false)
    public String address;

    @Column(name = "Phone", nullable = false)
    public String phone;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    public Cart cart;

    public Account() {
    }

    public Account(String email, String password, Role role, String name, String address, String phone, Cart cart) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.cart = cart;
    }

    public Account(int id, String email, String password, Role role, String name, String address, String phone, Cart cart) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.cart = cart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
