package com.example.coffeebe.domain.repositories;

import com.example.coffeebe.domain.entities.business.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
