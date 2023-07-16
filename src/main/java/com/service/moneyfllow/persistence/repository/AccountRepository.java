package com.service.moneyfllow.persistence.repository;

import com.service.moneyfllow.persistence.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account getAccountById(Integer id);
}
