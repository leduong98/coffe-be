package com.example.coffeebe.domain.repositories;

import com.example.coffeebe.domain.entities.author.Role;
import com.example.coffeebe.domain.entities.enums.RoleType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, Long> {
    Role findByRoleType(RoleType roleType);
}
