package com.example.coffeebe.domain.repositories;

import com.example.coffeebe.domain.entities.business.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
