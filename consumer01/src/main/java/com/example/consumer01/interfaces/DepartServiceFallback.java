package com.example.consumer01.interfaces;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.consumer01.bean.Depart;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

//@Component
@RequestMapping("/fallback/provider/depart")
public class DepartServiceFallback implements DepartService{
    @Override
    public boolean save(Depart depart) {
        System.err.println("save fallback ");
        return false;
    }

    @Override
    public boolean removeById(Integer id) {
        System.err.println("removeById fallback ");
        return false;
    }

    @Override
    public boolean update(Depart depart) {
        System.err.println("update fallback ");
        return false;
    }

    @Override
    public Depart getById(Integer id) {
        System.err.println("getById fallback ");
        return new Depart().setId(2).setName("fallback");
    }

    @Override
    public List<Depart> getList() {
        System.err.println("getList fallback ");
        Depart depart = new Depart().setId(2).setName("fallback");
        ArrayList<Depart> departs = new ArrayList<>();
        departs.add(depart);
        return departs;
    }
}
