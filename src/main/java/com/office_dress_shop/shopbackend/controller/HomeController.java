package com.office_dress_shop.shopbackend.controller;

import com.office_dress_shop.shopbackend.pojo.Account;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String homePage(HttpSession session, Model model) {
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            model.addAttribute("email", account.getEmail());
            model.addAttribute("role", account.getRole());
        }
        return "home";
    }
}

