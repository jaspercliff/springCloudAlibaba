package com.example.business.service;

import com.example.business.Bean.Stock;

public interface StockService {
    Stock deduct(Integer goodsId, Integer count);

}
