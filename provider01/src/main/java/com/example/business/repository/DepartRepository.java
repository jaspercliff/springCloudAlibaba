package com.example.business.repository;

import com.example.business.Bean.Depart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartRepository extends JpaRepository<Depart,Integer> {
}
