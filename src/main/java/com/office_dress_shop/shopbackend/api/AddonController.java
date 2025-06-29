package com.office_dress_shop.shopbackend.api;

import com.office_dress_shop.shopbackend.pojo.*;
import com.office_dress_shop.shopbackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/addons")
public class AddonController {
    @Autowired private AddonService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("addons", service.findAll());
        return "addon/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("addon", new Addon()); // Quan trọng!
        return "addon/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Addon addon) {
        service.save(addon);
        return "redirect:/addons";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        return service.findById(id)
                .map(a -> { model.addAttribute("addon", a); return "addon/edit"; })
                .orElse("redirect:/addons");
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id, @ModelAttribute Addon addon) {
        addon.setId(id);
        service.save(addon);
        return "redirect:/addons";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        service.deleteById(id);
        return "redirect:/addons";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable int id, Model model) {
        return service.findById(id)
                .map(a -> { model.addAttribute("addon", a); return "addon/detail"; })
                .orElse("redirect:/addons");
    }
}

