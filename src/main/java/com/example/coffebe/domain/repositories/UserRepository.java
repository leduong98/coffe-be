package com.example.coffebe.domain.repositories;

import com.example.coffebe.domain.entities.author.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {

}
