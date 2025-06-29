package com.office_dress_shop.shopbackend.service;

import com.office_dress_shop.shopbackend.pojo.Account;
import com.office_dress_shop.shopbackend.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccounServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccountById(int id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(int id, Account newAccount) {
        return accountRepository.findById(id)
                .map(account -> {
                    account.setEmail(newAccount.getEmail());
                    account.setPassword(newAccount.getPassword());
                    account.setRole(newAccount.getRole());
                    account.setIsActived(newAccount.getIsActived());
                    return accountRepository.save(account);
                }).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public boolean deleteAccount(int id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Account setActive(int id, boolean isActive) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setIsActived(isActive);
        return accountRepository.save(account);
    }
}
