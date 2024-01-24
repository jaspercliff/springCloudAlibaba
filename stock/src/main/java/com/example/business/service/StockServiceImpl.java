package com.example.business.service;

import com.example.business.Bean.Stock;
import com.example.business.repository.StockRepository;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
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

    @Override
    public Boolean tccCommit(BusinessActionContext context) {
        log.info("库存已经 commit");
        return true;
    }

    @Override
    public Boolean tccRollback(BusinessActionContext context) {
        log.info("库存已经 rollback");

        Integer goodsId = (Integer) context.getActionContext("goodsId");
        Integer count = (Integer) context.getActionContext("count");

        Stock stock = repository.findStockByGoodsId(goodsId);
        stock.setTotal(stock.getTotal()+count);
        repository.save(stock);
        return true;
    }
}
