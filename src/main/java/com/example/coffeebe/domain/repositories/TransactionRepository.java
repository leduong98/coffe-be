package com.example.coffeebe.domain.repositories;

import com.example.coffeebe.domain.entities.business.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT tr FROM Transaction tr WHERE tr.user.id = ?1")
    Page<Transaction> findAllByUser(Long userId, Pageable pageable);
}
