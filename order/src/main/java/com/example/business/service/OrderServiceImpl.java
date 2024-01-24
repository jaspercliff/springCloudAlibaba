package com.example.business.service;

import com.example.business.Bean.Orders;
import com.example.business.repository.OrdersRepository;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrdersRepository repository;
    private Integer orderId;

    @Override
    public Orders createOrder(Orders orders) {
        Orders order = repository.save(orders);
        this.orderId = order.getId();
        return order;
    }

    @Override
    public Boolean tccCommit(BusinessActionContext context) {
        log.info("order commit");
        return true;
    }

    @Override
    public Boolean tccRollback(BusinessActionContext context) {
        log.info("order rollback");
        repository.deleteById(orderId);
        return true;
    }
}
