package com.office_dress_shop.shopbackend.api;

import com.office_dress_shop.shopbackend.pojo.*;
import com.office_dress_shop.shopbackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/materials")
public class MaterialController {
    @Autowired private MaterialService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("materials", service.findAll());
        return "material/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("material", new Material());
        return "material/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Material material) {
        service.save(material);
        return "redirect:/materials";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        return service.findById(id)
                .map(m -> { model.addAttribute("material", m); return "material/edit"; })
                .orElse("redirect:/materials");
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id, @ModelAttribute Material material) {
        material.setId(id);
        service.save(material);
        return "redirect:/materials";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        service.deleteById(id);
        return "redirect:/materials";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable int id, Model model) {
        return service.findById(id)
                .map(m -> { model.addAttribute("material", m); return "material/detail"; })
                .orElse("redirect:/materials");
    }
}
