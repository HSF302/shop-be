package com.office_dress_shop.shopbackend.repository;

import com.office_dress_shop.shopbackend.pojo.OfficeDress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeDressRepository extends JpaRepository<OfficeDress, Integer> {
}