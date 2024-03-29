package com.example.business.controller;

import com.example.business.Bean.Depart;
import com.example.business.service.DepartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/depart")
@RequiredArgsConstructor
public class DepartController {
    private final DepartService departService;

    @Value("${server.port}")
    private String port;

    @GetMapping("/port")
    public String getPort(HttpServletRequest request){
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String[]> parameterMap = request.getParameterMap();
        return port;
    }
    @PostMapping("/")
    private boolean save(@RequestBody Depart depart){
        return departService.save(depart);
    }

    @DeleteMapping("/{id}")
    private boolean deleteDepart(@PathVariable Integer id){
        return departService.removeById(id);
    }

    @PutMapping("/")
    private boolean updateDepart(@RequestBody Depart depart){
        return departService.update(depart);
    }

    @GetMapping("/{id}")
    private Depart getById(@PathVariable Integer id){
        return departService.getById(id);
    }

    @GetMapping("/all")
    private List<Depart> list(){
        return departService.getList();
    }

    @GetMapping("/list")
    private List<Depart> departs(){
        return departService.getList();
    }
}
