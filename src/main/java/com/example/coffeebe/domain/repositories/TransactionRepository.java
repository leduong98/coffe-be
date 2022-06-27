package com.example.coffeebe.domain.repositories;

import com.example.coffeebe.domain.entities.business.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
