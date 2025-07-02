package com.office_dress_shop.shopbackend.controller;

import com.office_dress_shop.shopbackend.enums.Role;
import com.office_dress_shop.shopbackend.pojo.Account;
import com.office_dress_shop.shopbackend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/list")
    public String listAccounts(Model model) {
        model.addAttribute("accounts", accountService.findAll());
        return "account/list";
    }

    @GetMapping("/create")
    public String createAccountPage(Model model) {
        model.addAttribute("account", new Account());
        return "account/create";
    }

    @PostMapping("/create")
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
        account.setPassword(passwordEncoder.encode(password));
        account.setRole(Role.valueOf(role.trim().toUpperCase())); // Ép thành Enum ở đây
        account.setName(name);
        account.setAddress(address);
        account.setPhone(phone);
        account.setIsActived(true);

        accountService.save(account);

        model.addAttribute("message", "Tạo tài khoản thành công!");
        return "redirect:/admin/accounts/list";
    }


    @GetMapping("/edit/{id}")
    public String editAccountPage(@PathVariable int id, Model model) {
        var account = accountService.findById(id);
        if (account.isPresent()) {
            model.addAttribute("account", account.get());
            return "account/edit";
        }
        return "redirect:/admin/accounts/list"; // Nếu không tìm thấy tài khoản
    }

    @PostMapping("/edit/{id}")
    public String editAccount(@PathVariable int id, @ModelAttribute Account account, Model model) {
        var existingAccount = accountService.findById(id);
        if (existingAccount.isPresent()) {
            Account old = existingAccount.get();
            // Cập nhật từng trường từ account gửi lên
            old.setEmail(account.getEmail());
            old.setName(account.getName());
            old.setAddress(account.getAddress());
            old.setPhone(account.getPhone());
            old.setRole(account.getRole());
            old.setIsActived(account.getIsActived());
            // Xử lý password: nếu nhập mới thì đổi, không nhập thì giữ nguyên
            if (account.getPassword() != null && !account.getPassword().isBlank()) {
                old.setPassword(passwordEncoder.encode(account.getPassword()));
            }
            // Nếu password rỗng thì không update (giữ nguyên)
            accountService.save(old);
            model.addAttribute("message", "Cập nhật tài khoản thành công!");
        } else {
            model.addAttribute("error", "Không tìm thấy tài khoản!");
        }
        return "redirect:/admin/accounts/list";
    }


    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable int id, Model model) {
        accountService.deleteById(id);
        model.addAttribute("message", "Tài khoản đã bị vô hiệu hóa!");
        return "redirect:/admin/accounts/list";
    }
}
