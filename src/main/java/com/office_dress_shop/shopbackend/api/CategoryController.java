package com.office_dress_shop.shopbackend.api;

import com.office_dress_shop.shopbackend.pojo.*;
import com.office_dress_shop.shopbackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired private CategoryService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", service.findAll());
        return "category/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Category category) {
        service.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        return service.findById(id)
                .map(c -> { model.addAttribute("category", c); return "category/edit"; })
                .orElse("redirect:/categories");
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id, @ModelAttribute Category category) {
        category.setId(id);
        service.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        service.deleteById(id);
        return "redirect:/categories";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable int id, Model model) {
        return service.findById(id)
                .map(c -> { model.addAttribute("category", c); return "category/detail"; })
                .orElse("redirect:/categories");
    }
}
