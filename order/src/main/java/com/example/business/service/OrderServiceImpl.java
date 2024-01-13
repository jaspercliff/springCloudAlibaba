package com.example.business.service;

import com.example.business.Bean.Orders;
import com.example.business.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrdersRepository repository;

    @Override
    public Orders createOrder(Orders orders) {
        return repository.save(orders);
    }
}
