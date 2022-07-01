package com.example.coffeebe.domain.repositories;

import com.example.coffeebe.domain.entities.author.User;
import com.example.coffeebe.domain.entities.business.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    Page<Transaction> findAllByUser(User user, Pageable pageable);
}
