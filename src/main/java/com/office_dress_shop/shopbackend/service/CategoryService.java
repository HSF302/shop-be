package com.office_dress_shop.shopbackend.service;

import com.office_dress_shop.shopbackend.pojo.*;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Optional<Category> findById(int id);
    Category save(Category category);
    void deleteById(int id);
}
