package com.example.provider01.repository;

import com.example.provider01.Bean.Depart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartRepository extends JpaRepository<Depart,Integer> {
}
