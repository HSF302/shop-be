package com.office_dress_shop.shopbackend.controller;

import com.office_dress_shop.shopbackend.pojo.Account;
import com.office_dress_shop.shopbackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer/profile")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Xem profile
    @GetMapping
    public String viewProfile(Model model, @SessionAttribute("loggedInUserId") int userId) {
        return customerService.findById(userId)
                .map(acc -> {
                    model.addAttribute("account", acc);
                    return "customer/profile";
                })
                .orElse("redirect:/login");
    }

    // Form update
    @GetMapping("/edit")
    public String editProfilePage(Model model, @SessionAttribute("loggedInUserId") int userId) {
        return customerService.findById(userId)
                .map(acc -> {
                    model.addAttribute("account", acc);
                    return "customer/edit_profile";
                })
                .orElse("redirect:/login");
    }

    // Xử lý update profile (chỉ update thông tin cá nhân, không update role/email)
    @PostMapping("/edit")
    public String updateProfile(@ModelAttribute Account account,
                                Model model,
                                @SessionAttribute("loggedInUserId") int userId) {
        Account updated = customerService.updateProfile(userId, account);
        if (updated != null) {
            model.addAttribute("message", "Cập nhật thông tin thành công!");
        } else {
            model.addAttribute("error", "Không tìm thấy tài khoản!");
        }
        return "redirect:/customer/profile";
    }
}
