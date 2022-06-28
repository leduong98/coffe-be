package com.example.coffeebe;

import com.example.coffeebe.domain.services.impl.file_service.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})

public class CoffeeBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeBeApplication.class, args);
    }

}
