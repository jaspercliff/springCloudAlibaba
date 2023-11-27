package com.example.provider01.service;

import com.example.provider01.Bean.Depart;
import com.example.provider01.repository.DepartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DepartServiceImpl implements DepartService{
    private final DepartRepository departRepository;
    @Override
    public boolean save(Depart depart) {
        departRepository.save(depart);
        return true;
    }

    @Override
    public boolean removeById(int id) {
        if (departRepository.existsById(id)){
            departRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Depart depart) {
        departRepository.save(depart);
        return true;
    }

    @Override
    public Depart getById(int id) {
        if (departRepository.existsById(id)){
            return departRepository.getReferenceById(id);
        }
        return null;
    }

    @Override
    public List<Depart> getList() {
        return departRepository.findAll();
    }
}
