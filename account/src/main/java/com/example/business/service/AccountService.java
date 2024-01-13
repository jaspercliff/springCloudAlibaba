package com.example.business.service;

import com.example.business.Bean.Account;

public interface AccountService {

    Account debitAccount(Integer userId, double amount);
}
