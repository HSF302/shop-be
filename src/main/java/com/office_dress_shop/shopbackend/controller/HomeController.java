package com.office_dress_shop.shopbackend.controller;

import com.office_dress_shop.shopbackend.pojo.Account;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping
    public String homePage(HttpSession session, Model model) {
        if (session.getAttribute("account") == null) {
        return "home";
        }
        Account account = (Account) session.getAttribute("account");
        model.addAttribute("email", account.getEmail());
        model.addAttribute("role", account.getRole());
        return "home";
    }
}
