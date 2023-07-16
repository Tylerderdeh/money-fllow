package com.service.moneyfllow.persistence.repository;

import com.service.moneyfllow.persistence.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
