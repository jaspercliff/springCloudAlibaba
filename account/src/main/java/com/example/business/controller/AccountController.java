package com.example.business.controller;

import com.example.business.Bean.Account;
import com.example.business.service.AccountService;
import io.seata.core.context.RootContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService service;

    @PutMapping("/")
    private Account debit(
            @RequestParam("userId")
            Integer userId,
            @RequestParam("amount")
            double amount){
        System.err.println(RootContext.getXID());
        return service.debitAccount(userId,amount);
    }

}
