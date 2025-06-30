package com.office_dress_shop.shopbackend.service;

import com.office_dress_shop.shopbackend.pojo.*;
import com.office_dress_shop.shopbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialServiceImpl implements MaterialService {
    @Autowired private MaterialRepository repo;
    public List<Material> findAll() { return repo.findAll(); }
    public Optional<Material> findById(int id) { return repo.findById(id); }
    public Material save(Material material) { return repo.save(material); }
    public void deleteById(int id) { repo.deleteById(id); }
}
