package com.example.consumer01.contorller;

import com.example.consumer01.bean.Depart;
import com.example.consumer01.interfaces.DepartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depart")
@RequiredArgsConstructor
public class DepartFeignController {
    private final DepartService service;
    @Value("${server.port}")
    private String port;

    @GetMapping("/port")
    public String getPort(){
        return port;
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Depart depart){
        return service.save(depart);
    }

    @DeleteMapping("/{id}")
    private boolean delete(@PathVariable Integer id){
        return service.removeById(id);
    }

    @PutMapping("/")
    private boolean update(@RequestBody Depart depart){
        return service.update(depart);
    }

    @GetMapping("/{id}")
    private Depart getById(@PathVariable Integer id){
        return service.getById(id);
    }
    @GetMapping("/")
    private List<Depart> getList(){
        return service.getList();
    }
}
