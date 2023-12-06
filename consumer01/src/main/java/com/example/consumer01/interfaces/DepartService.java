package com.example.consumer01.interfaces;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.consumer01.bean.Depart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "provide-service",
             path = "/provider/depart")
public interface DepartService {
    @PostMapping("/")
    boolean save(@RequestBody Depart depart);
    @DeleteMapping("/{id}")
    boolean removeById(@PathVariable Integer id);
    @PutMapping("/")
    boolean update(@RequestBody Depart depart);
    @GetMapping("/{id}")
    Depart getById(@PathVariable Integer id);
    @GetMapping("/")
    List<Depart> getList();


}
