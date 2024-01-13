package com.example.business.service;

import com.example.business.Bean.Depart;

import java.util.List;

public interface DepartService {
    boolean save(Depart depart);
    boolean removeById(int id);
    boolean update(Depart depart);
    Depart getById(int id);
    List<Depart> getList();
}
