package com.office_dress_shop.shopbackend.service;

import com.office_dress_shop.shopbackend.pojo.*;
import com.office_dress_shop.shopbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired private CategoryRepository repo;
    public List<Category> findAll() { return repo.findAll(); }
    public Optional<Category> findById(int id) { return repo.findById(id); }
    public Category save(Category category) { return repo.save(category); }
    public void deleteById(int id) { repo.deleteById(id); }
}
