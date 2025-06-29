package com.office_dress_shop.shopbackend.controller;

import com.office_dress_shop.shopbackend.enums.Role;
import com.office_dress_shop.shopbackend.pojo.Account;
import com.office_dress_shop.shopbackend.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        HttpServletResponse response,
                        Model model) {
        Account account = authenticationService.authenticate(email, password);
        if (account != null) {
            session.setMaxInactiveInterval(30 * 60); // 30 minutes session timeout
            session.setAttribute("account", account);
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            return "redirect:/home";
        }

        model.addAttribute("error", "Sai tài khoản hoặc mật khẩu!");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email, Model model) {
        boolean result = authenticationService.forgotPassword(email);
        if (result) {
            model.addAttribute("message", "Vui lòng kiểm tra email để đặt lại mật khẩu!");
        } else {
            model.addAttribute("error", "Không tìm thấy tài khoản!");
        }
        return "forgot-password";
    }

    @GetMapping("/reset-password")
    public String resetPasswordPage(@RequestParam String token, Model model) {
        model.addAttribute("token", token);
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(
            @RequestParam String token,
            @RequestParam String newPassword,
            Model model) {
        boolean result = authenticationService.resetPassword(token, newPassword);
        if (result) {
            model.addAttribute("message", "Đổi mật khẩu thành công! Vui lòng đăng nhập lại.");
            return "login";
        } else {
            model.addAttribute("error", "Token không hợp lệ hoặc đã hết hạn!");
            model.addAttribute("token", token);
            return "reset-password";
        }
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("account", new Account());
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute("account") Account account,
            Model model) {
        if (authenticationService.emailExists(account.getEmail())) {
            model.addAttribute("error", "Email đã tồn tại!");
            return "register";
        }
        boolean success = authenticationService.register(account);
        if (success) {
            model.addAttribute("message", "Đăng ký thành công! Vui lòng đăng nhập.");
            return "login";
        } else {
            model.addAttribute("error", "Đăng ký thất bại! Vui lòng thử lại.");
            return "register";
        }
    }

@GetMapping("/home")
public String homePage(HttpSession session, Model model) {
    Account account = (Account) session.getAttribute("account");
    model.addAttribute("email", account.getEmail());
    model.addAttribute("role", account.getRole());
    return "home";
}


}

