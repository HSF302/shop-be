package com.office_dress_shop.shopbackend.service;

import com.office_dress_shop.shopbackend.pojo.*;
import java.util.List;
import java.util.Optional;

public interface ColorService {
    List<Color> findAll();
    Optional<Color> findById(int id);
    Color save(Color color);
    void deleteById(int id);
}
