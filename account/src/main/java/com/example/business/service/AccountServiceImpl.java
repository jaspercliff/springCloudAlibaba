package com.example.business.service;

import com.example.business.Bean.Account;
import com.example.business.repository.AccountRepository;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
@Slf4j
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

    @Override
    public Boolean tccCommit(BusinessActionContext context) {
        log.info("账户变更commit");
        return true;
    }

    @Override
    public Boolean tccRollback(BusinessActionContext context) {

        Integer userId = (Integer)context.getActionContext("userId");
        BigDecimal amount = (BigDecimal)context.getActionContext("amount");

        Account account = repository.findAccountByUserId(userId);
        account.setBalance(account.getBalance()+amount.doubleValue());
        repository.save(account);
        return true;
    }
}
