package com.example.business.controller;


import com.jasper.bean.Orders;
import com.jasper.bean.Stock;
import com.jasper.service.OrderService;
import com.jasper.service.StockService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.GlobalTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static io.seata.tm.api.GlobalTransactionContext.getCurrent;

@RestController
@RequiredArgsConstructor
@RequestMapping("/business")
public class BusinessController {
    private final OrderService service;

    private final StockService stockService;

//    @RequestParam("userId") Integer userId,
//    @RequestParam("goodsId") Integer goodsId,
//    @RequestParam("goodsPrice") double goodsPrice,
//    @RequestParam("count") Integer count
    @PostMapping("/")
    @GlobalTransactional(rollbackFor = Exception.class)
    public String purchaseHandle(@RequestBody Orders orders) {
        System.err.println(RootContext.getXID());
            Stock deduct = stockService.deduct(orders.getGoodsId(), orders.getCount());
            if (deduct == null){
                return "库存不足，下单失败";
            }
            Orders order = service.createOrder(orders);
            if (order == null){
                return "账户余额不足,下单失败";
            }

            int i = 3/0;

            return "下单成功";
    }

}
