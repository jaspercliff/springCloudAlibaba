package com.example.business.service;

import com.example.business.Bean.Stock;
import com.example.business.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StockServiceImpl implements StockService{
    private final StockRepository repository;
    @Override
    public Stock deduct(Integer goodsId, Integer count) {
        Stock stock = repository.findStockByGoodsId(goodsId);
        if (stock == null || stock.getTotal() <count) {
            return  null;
        }
        stock.setTotal(stock.getTotal()-count);
        repository.save(stock);
        return stock;
    }
}
