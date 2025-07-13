package com.office_dress_shop.shopbackend.controller;

import com.office_dress_shop.shopbackend.enums.Role;
import com.office_dress_shop.shopbackend.pojo.Account;
import com.office_dress_shop.shopbackend.pojo.Cart;
import com.office_dress_shop.shopbackend.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @ModelAttribute
    public void addRoleToModel(HttpSession session, Model model) {
        Account acc = (Account) session.getAttribute("account");
        if (acc != null) {
            model.addAttribute("role", acc.getRole().name());
        }
    }

    @GetMapping
    public String list(Model model, HttpSession session) {
        Account acc = (Account) session.getAttribute("account");

        if (acc.getRole() == Role.ADMIN) {
            model.addAttribute("carts", cartService.findAll());
        } else {
            Cart cart = cartService.getOrCreateCart(acc);
            model.addAttribute("carts", java.util.List.of(cart));
        }
        return "cart/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable int id, Model model, HttpSession session) {
        Account acc = (Account) session.getAttribute("account");

        return cartService.findById(id)
                .filter(cart -> acc.getRole() == Role.ADMIN || cart.getAccount().getId() == acc.getId())
                .map(cart -> {
                    model.addAttribute("cart", cart);
                    return "cart/detail";
                })
                .orElse("redirect:/carts");
    }

}
