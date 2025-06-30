package com.office_dress_shop.shopbackend.service;

import com.office_dress_shop.shopbackend.pojo.OfficeDress;

import java.util.List;
import java.util.Optional;

public interface OfficeDressService {
    List<OfficeDress> findAll();
    Optional<OfficeDress> findById(int id);
    OfficeDress save(OfficeDress officeDress);
    void deleteById(int id);
}