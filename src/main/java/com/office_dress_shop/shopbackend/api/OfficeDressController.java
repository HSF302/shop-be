package com.office_dress_shop.shopbackend.api;

import com.office_dress_shop.shopbackend.pojo.OfficeDress;
import com.office_dress_shop.shopbackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/officedresses")
public class OfficeDressController {

    @Autowired private OfficeDressService officeDressService;
    @Autowired private SizeService sizeService;
    @Autowired private ColorService colorService;
    @Autowired private MaterialService materialService;
    @Autowired private CategoryService categoryService;
    @Autowired private AddonService addonService;

    @GetMapping
    public String listOfficeDresses(Model model) {
        model.addAttribute("dresses", officeDressService.findAll());
        return "officedress/list"; // Trả về view: templates/officedress/list.html
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("officeDress", new OfficeDress());
        model.addAttribute("sizes", sizeService.findAll());
        model.addAttribute("colors", colorService.findAll());
        model.addAttribute("materials", materialService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("addons", addonService.findAll());
        return "officedress/add";
    }


    @PostMapping("/add")
    public String addOfficeDress(@ModelAttribute OfficeDress officeDress) {
        officeDressService.save(officeDress);
        return "redirect:/officedresses";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        return officeDressService.findById(id)
                .map(dress -> {
                    model.addAttribute("officeDress", dress);
                    model.addAttribute("sizes", sizeService.findAll());
                    model.addAttribute("colors", colorService.findAll());
                    model.addAttribute("materials", materialService.findAll());
                    model.addAttribute("categories", categoryService.findAll());
                    model.addAttribute("addons", addonService.findAll());
                    return "officedress/edit";
                })
                .orElse("redirect:/officedresses");
    }

    @PostMapping("/edit/{id}")
    public String updateOfficeDress(@PathVariable int id, @ModelAttribute OfficeDress officeDress) {
        officeDress.setId(id);
        officeDressService.save(officeDress);
        return "redirect:/officedresses";
    }

    @GetMapping("/delete/{id}")
    public String deleteOfficeDress(@PathVariable int id) {
        officeDressService.deleteById(id);
        return "redirect:/officedresses";
    }

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable int id, Model model) {
        return officeDressService.findById(id)
                .map(dress -> {
                    model.addAttribute("officeDress", dress);
                    return "officedress/detail"; // Trả về view: templates/officedress/detail.html
                })
                .orElse("redirect:/officedresses");
    }
}
