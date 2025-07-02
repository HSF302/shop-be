package com.office_dress_shop.shopbackend.service;

import com.office_dress_shop.shopbackend.pojo.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> findAll();
    Optional<Account> findById(int id);
    Account save(Account account);
    void deleteById(int id);
    boolean createAccountByAdmin(Account account);
}
