package com.office_dress_shop.shopbackend.controller;

import com.office_dress_shop.shopbackend.enums.Role;
import com.office_dress_shop.shopbackend.pojo.Account;
import com.office_dress_shop.shopbackend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping("/list")
    public String listAccounts(Model model) {
        model.addAttribute("accounts", accountService.findAll());
        return "account/list";
    }

    @GetMapping("/list/create")
    public String createAccountPage(Model model) {
        model.addAttribute("account", new Account());
        return "account/create";
    }

    @PostMapping("/list/create")
    public String createAccount(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String role,   // Nhận trực tiếp là String
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String phone,
            Model model
    ) {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        account.setRole(Role.valueOf(role.trim().toUpperCase())); // Ép thành Enum ở đây
        account.setName(name);
        account.setAddress(address);
        account.setPhone(phone);
        account.setIsActived(true);

        accountService.save(account);

        model.addAttribute("message", "Tạo tài khoản thành công!");
        return "redirect:/accounts/list";
    }


    @GetMapping("/list/edit/{id}")
    public String editAccountPage(@PathVariable int id, Model model) {
        var account = accountService.findById(id);
        if (account.isPresent()) {
            model.addAttribute("account", account.get());
            return "account/edit";
        }
        return "redirect:/accounts/list"; // Nếu không tìm thấy tài khoản
    }

    @PostMapping("/list/edit/{id}")
    public String editAccount(@PathVariable int id, @ModelAttribute Account account, Model model) {
        var existingAccount = accountService.findById(id);
        if (existingAccount.isPresent()) {
            account.setId(id);
            accountService.save(account);
            model.addAttribute("message", "Cập nhật tài khoản thành công!");
        } else {
            model.addAttribute("error", "Không tìm thấy tài khoản!");
        }
        return "redirect:/accounts/list";
    }

    @GetMapping("/list/delete/{id}")
    public String deleteAccount(@PathVariable int id, Model model) {
        accountService.deleteById(id);
        model.addAttribute("message", "Tài khoản đã bị vô hiệu hóa!");
        return "redirect:/accounts/list";
    }
}
