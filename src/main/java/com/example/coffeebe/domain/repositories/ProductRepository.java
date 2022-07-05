package com.example.coffeebe.domain.repositories;

import com.example.coffeebe.domain.entities.business.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);
    @Query("SELECT p FROM Product p WHERE p.category.id = ?1 GROUP BY p.id ")
    Page<Product> findAllByCategory(Long categoryId, Pageable pageable);

}
