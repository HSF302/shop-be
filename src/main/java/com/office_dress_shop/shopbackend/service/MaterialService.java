package com.office_dress_shop.shopbackend.service;

import com.office_dress_shop.shopbackend.pojo.*;
import java.util.List;
import java.util.Optional;

public interface MaterialService {
    List<Material> findAll();
    Optional<Material> findById(int id);
    Material save(Material material);
    void deleteById(int id);
}
