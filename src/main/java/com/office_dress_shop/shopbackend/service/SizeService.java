package com.office_dress_shop.shopbackend.service;

import com.office_dress_shop.shopbackend.pojo.*;
import java.util.List;
import java.util.Optional;

public interface SizeService {
    List<Size> findAll();
    Optional<Size> findById(int id);
    Size save(Size size);
    void deleteById(int id);
}
