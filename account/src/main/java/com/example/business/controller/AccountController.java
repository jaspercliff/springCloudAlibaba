package com.example.business.controller;

import com.example.business.Bean.Account;
import com.example.business.service.AccountService;
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
        return service.debitAccount(userId,amount);
    }

}
