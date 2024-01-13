package com.example.business.repository;

import com.example.business.Bean.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findAccountByUserId(Integer userId);
}
