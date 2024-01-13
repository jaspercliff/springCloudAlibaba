package com.example.business.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.business.Bean.Depart;
import com.example.business.repository.DepartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @SentinelResource(value = "departListHandle",blockHandler = "handleListBlock")
    public List<Depart> getList() {
        return departRepository.findAll();
    }

    public List<Depart> handleListBlock(BlockException e) {
        ArrayList<Depart> departs = new ArrayList<>();
        Depart depart = new Depart();
        depart.setId(2).setName("depart list sentinel flow fallback !!");
        departs.add(depart);
        return departs;
    }
}
