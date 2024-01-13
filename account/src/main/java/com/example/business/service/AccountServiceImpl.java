package com.example.business.service;

import com.example.business.Bean.Account;
import com.example.business.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;


    @Override
    public Account debitAccount(Integer userId, double amount) {
        Account account = repository.findAccountByUserId(userId);
        if (account == null || account.getBalance() < amount) {
            return null;
        }
        account.setBalance(account.getBalance()-amount);
        repository.save(account);
        return account;
    }
}
