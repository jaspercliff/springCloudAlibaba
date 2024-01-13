package com.jasper.service;


import com.jasper.bean.Orders;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("order-service")
public interface OrderService {

    @PostMapping("/order/")
    Orders createOrder(@RequestBody Orders orders);

}
