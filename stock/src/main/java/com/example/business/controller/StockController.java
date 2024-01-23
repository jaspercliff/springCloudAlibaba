package com.example.business.controller;

import com.example.business.Bean.Stock;
import com.example.business.service.StockService;
import io.seata.core.context.RootContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("stock")
public class StockController {
    private final StockService service;

    @PutMapping("/")
    private Stock updateDepart(
            @RequestParam("goodsId")
            Integer goodsId,
            @RequestParam("count")
            Integer count){
        System.err.println(RootContext.getXID());
        return service.deduct(goodsId,count);
    }

}
