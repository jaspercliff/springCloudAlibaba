package com.example.business.controller;

import com.example.business.Bean.Orders;
import com.example.business.service.OrderService;
import com.jasper.bean.Account;
import com.jasper.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService service;

    private final AccountService accountService;
    @PostMapping("/")
     private Orders debit(@RequestBody Orders orders){
        double total = orders.getGoodsPrice() * orders.getCount();
        Account account = accountService.debitAccount(orders.getUserId(), total);
        if (account ==null){
            return null;
        }
        return service.createOrder(orders);
    }

}
