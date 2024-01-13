package com.jasper.service;


import com.jasper.bean.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("account-service")
public interface AccountService {
    @PutMapping("/account/")
    Account debitAccount(@RequestParam("userId") Integer userId, @RequestParam("amount") double amount);
}
