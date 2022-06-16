package com.example.coffeebe.domain.repositories;

import com.example.coffeebe.domain.entities.author.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, Long> {
    User findByEmail(String email);

    boolean existsByEmail(String email);

}
