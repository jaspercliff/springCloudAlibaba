package com.example.business.repository;

import com.example.business.Bean.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,Integer> {
    Stock findStockByGoodsId(Integer goodsId);
}
