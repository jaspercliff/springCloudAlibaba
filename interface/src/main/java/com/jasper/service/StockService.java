package com.jasper.service;


import com.jasper.bean.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("stock-service")
public interface StockService {
    @PutMapping("/stock/")
    Stock deduct(@RequestParam("goodsId") Integer goodsId, @RequestParam("count") Integer count);

}
