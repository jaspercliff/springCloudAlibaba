package com.example.provider01.controller;

import com.example.provider01.Bean.Depart;
import com.example.provider01.service.DepartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/depart")
@RequiredArgsConstructor
public class DepartController {
    private final DepartService departService;

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

    @GetMapping("/")
    private List<Depart> list(){
        return departService.getList();
    }

}
