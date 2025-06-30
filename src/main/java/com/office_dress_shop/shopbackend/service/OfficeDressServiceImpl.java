package com.office_dress_shop.shopbackend.service;

import com.office_dress_shop.shopbackend.pojo.OfficeDress;
import com.office_dress_shop.shopbackend.repository.OfficeDressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfficeDressServiceImpl implements OfficeDressService {

    @Autowired
    private OfficeDressRepository officeDressRepository;

    @Override
    public List<OfficeDress> findAll() {
        return officeDressRepository.findAll();
    }

    @Override
    public Optional<OfficeDress> findById(int id) {
        return officeDressRepository.findById(id);
    }

    @Override
    public OfficeDress save(OfficeDress officeDress) {
        return officeDressRepository.save(officeDress);
    }

    @Override
    public void deleteById(int id) {
        officeDressRepository.deleteById(id);
    }
}