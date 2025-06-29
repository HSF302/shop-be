package com.office_dress_shop.shopbackend.service;

import com.office_dress_shop.shopbackend.pojo.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> getAllAccounts();
    Optional<Account> getAccountById(int id);
    Account createAccount(Account account);
    Account updateAccount(int id, Account account);
    boolean deleteAccount(int id);
    Account setActive(int id, boolean isActive);
}
