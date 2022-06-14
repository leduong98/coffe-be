package com.example.coffebe.domain.configs;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
public interface MongoOperationsConfig extends MongoOperations {
    // fixing autowire bug
}
