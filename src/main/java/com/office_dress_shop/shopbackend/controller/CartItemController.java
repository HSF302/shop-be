package com.office_dress_shop.shopbackend.controller;

import com.office_dress_shop.shopbackend.enums.Role;
import com.office_dress_shop.shopbackend.pojo.*;
import com.office_dress_shop.shopbackend.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart-items")
public class CartItemController {

    @Autowired private CartItemService cartItemService;
    @Autowired private CartService cartService;
    @Autowired private OfficeDressService officeDressService;
    @Autowired private SizeService sizeService;
    @Autowired private ColorService colorService;
    @Autowired private MaterialService materialService;
    @Autowired private AddonService addonService;
    @Autowired private AccountService accountService;

    @ModelAttribute
    public void addRoleToModel(HttpSession session, Model model) {
        Account acc = (Account) session.getAttribute("account");
        if (acc != null) {
            model.addAttribute("role", acc.getRole().name());
        }
    }

    @GetMapping
    public String list(@RequestParam(required = false) Integer cartId,
                       Model model, HttpSession session) {
        Account acc = (Account) session.getAttribute("account");
        if (acc == null) return "redirect:/login";

        model.addAttribute("role", acc.getRole().name());

        if (acc.getRole() == Role.ADMIN && cartId != null) {
            Cart selectedCart = cartService.findById(cartId).orElse(null);
            if (selectedCart != null) {
                model.addAttribute("cartItems", cartItemService.findByCartId(cartId));
                model.addAttribute("selectedCart", selectedCart);
            } else {
                return "redirect:/carts";
            }
        } else {
            Cart cart = cartService.getOrCreateCart(acc);
            model.addAttribute("cartItems", cartItemService.findByCartId(cart.getId()));
            model.addAttribute("selectedCart", cart);
        }

        return "cartitem/list";
    }



    @GetMapping("/add")
    public String addForm(@RequestParam(required = false) Integer cartId,
                          Model model,
                          HttpSession session) {
        Account acc = (Account) session.getAttribute("account");
        if (acc == null) return "redirect:/login";

        CartItem cartItem = new CartItem();

        if (acc.getRole() == Role.ADMIN && cartId != null) {
            Cart selectedCart = cartService.findById(cartId).orElse(null);
            if (selectedCart != null) {
                cartItem.setCart(selectedCart);
                model.addAttribute("fixedCart", true);
            }
        }

        model.addAttribute("cartItem", cartItem);
        model.addAttribute("products", officeDressService.findAll());
        model.addAttribute("sizes", sizeService.findAll());
        model.addAttribute("colors", colorService.findAll());
        model.addAttribute("materials", materialService.findAll());
        model.addAttribute("addons", addonService.findAll());

        if (acc.getRole() == Role.ADMIN && cartId == null) {
            model.addAttribute("accounts", accountService.findAll());
        }

        return "cartitem/add";
    }



    @PostMapping("/add")
    public String add(@ModelAttribute CartItem cartItem,
                      @RequestParam(required = false) Integer cartId,
                      @RequestParam(required = false) Integer accountId,
                      HttpSession session) {
        Account acc = (Account) session.getAttribute("account");
        if (acc == null) return "redirect:/login";

        Cart finalCart = null;


        if (acc.getRole() == Role.ADMIN) {
            if (cartId != null) {
                finalCart = cartService.findById(cartId).orElse(null);
                if (finalCart == null) return "redirect:/cart-items";
            } else if (accountId != null) {
                Account selectedAcc = accountService.findById(accountId).orElse(null);
                if (selectedAcc == null) return "redirect:/cart-items";
                finalCart = cartService.getOrCreateCart(selectedAcc);
            } else {
                return "redirect:/cart-items";
            }
        } else {
            finalCart = cartService.getOrCreateCart(acc);
        }

        cartItem.setCart(finalCart);
        cartItemService.save(cartItem);

        return "redirect:/cart-items?cartId=" + finalCart.getId();
    }




    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model, HttpSession session) {
        Account acc = (Account) session.getAttribute("account");
        if (acc == null) return "redirect:/login";

        return cartItemService.findById(id)
                .filter(item -> acc.getRole() == Role.ADMIN || item.getCart().getAccount().getId() == acc.getId())
                .map(item -> {
                    model.addAttribute("cartItem", item);
                    model.addAttribute("products", officeDressService.findAll());
                    model.addAttribute("sizes", sizeService.findAll());
                    model.addAttribute("colors", colorService.findAll());
                    model.addAttribute("materials", materialService.findAll());
                    model.addAttribute("addons", addonService.findAll());
                    return "cartitem/edit";
                })
                .orElse("redirect:/cart-items");
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id, @ModelAttribute CartItem cartItem, HttpSession session) {
        Account acc = (Account) session.getAttribute("account");
        if (acc == null) return "redirect:/login";

        CartItem existing = cartItemService.findById(id).orElse(null);
        if (existing == null ||
                (acc.getRole() != Role.ADMIN && existing.getCart().getAccount().getId() != acc.getId())) {
            return "redirect:/cart-items";
        }

        cartItem.setId(id);
        cartItem.setCart(existing.getCart());
        cartItemService.save(cartItem);
        return "redirect:/cart-items";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable int id, Model model, HttpSession session) {
        Account acc = (Account) session.getAttribute("account");
        if (acc == null) return "redirect:/login";

        return cartItemService.findById(id)
                .filter(item -> acc.getRole() == Role.ADMIN || item.getCart().getAccount().getId() == acc.getId())
                .map(item -> {
                    model.addAttribute("cartItem", item);
                    return "cartitem/detail";
                })
                .orElse("redirect:/cart-items");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, HttpSession session) {
        Account acc = (Account) session.getAttribute("account");
        if (acc == null) return "redirect:/login";

        CartItem item = cartItemService.findById(id).orElse(null);
        if (item == null) return "redirect:/cart-items";

        if (acc.getRole() != Role.ADMIN && item.getCart().getAccount().getId() != acc.getId()) {
            return "redirect:/cart-items";
        }

        int cartId = item.getCart().getId();

        cartItemService.deleteById(id);

        if (acc.getRole() == Role.ADMIN) {
            return "redirect:/cart-items?cartId=" + cartId;
        }

        return "redirect:/cart-items";
    }

}
