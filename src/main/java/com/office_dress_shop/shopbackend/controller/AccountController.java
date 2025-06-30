package com.office_dress_shop.shopbackend.controller;

import com.office_dress_shop.shopbackend.pojo.Account;
import com.office_dress_shop.shopbackend.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Hiển thị danh sách tài khoản`
    @GetMapping
    public String listAccounts(HttpSession session, Model model) {
        String role = (String) session.getAttribute("USER_ROLE");
        if (role == null || !role.equals("ADMIN")) {
            return "redirect:/auth/login";
        }
        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "account-list"; // View hiển thị danh sách tài khoản
    }

    // Hiển thị form tạo mới tài khoản
    @GetMapping("/create")
    public String showCreateForm(HttpSession session, Model model) {
        String role = (String) session.getAttribute("USER_ROLE");
        if (role == null || !role.equals("ADMIN")) {
            return "redirect:/login";
        }
        model.addAttribute("account", new Account());
        return "account-form"; // View form tạo/sửa tài khoản
    }

    // Xử lý tạo mới tài khoản
    @PostMapping("/create")
    public String createAccount(@ModelAttribute("account") Account account,
                                HttpSession session, Model model) {
        String role = (String) session.getAttribute("USER_ROLE");
        if (role == null || !role.equals("ADMIN")) {
            return "redirect:/login";
        }
        accountService.createAccount(account);
        return "redirect:/admin/accounts";
    }

    // Hiển thị form cập nhật tài khoản
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, HttpSession session, Model model) {
        String role = (String) session.getAttribute("USER_ROLE");
        if (role == null || !role.equals("ADMIN")) {
            return "redirect:/login";
        }
        Account account = accountService.getAccountById(id).orElse(null);
        if (account == null) {
            return "redirect:/admin/accounts";
        }
        model.addAttribute("account", account);
        return "account-form"; // Sử dụng lại form
    }

    // Xử lý cập nhật tài khoản
    @PostMapping("/edit/{id}")
    public String updateAccount(@PathVariable Integer id,
                                @ModelAttribute("account") Account account,
                                HttpSession session) {
        String role = (String) session.getAttribute("USER_ROLE");
        if (role == null || !role.equals("ADMIN")) {
            return "redirect:/login";
        }
        accountService.updateAccount(id, account);
        return "redirect:/admin/accounts";
    }

    // "Xóa" tài khoản: chỉ set isActived = false
    @GetMapping("/delete/{id}")
    public String softDeleteAccount(@PathVariable Integer id, HttpSession session) {
        String role = (String) session.getAttribute("USER_ROLE");
        if (role == null || !role.equals("ADMIN")) {
            return "redirect:/login";
        }
        accountService.setActive(id, false);
        return "redirect:/admin/accounts";
    }

    // Kích hoạt lại tài khoản (nếu cần)
    @GetMapping("/activate/{id}")
    public String activateAccount(@PathVariable Integer id, HttpSession session) {
        String role = (String) session.getAttribute("USER_ROLE");
        if (role == null || !role.equals("ADMIN")) {
            return "redirect:/login";
        }
        accountService.setActive(id, true);
        return "redirect:/admin/accounts";
    }
}
