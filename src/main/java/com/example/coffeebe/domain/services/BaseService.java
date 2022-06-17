package com.example.coffeebe.domain.services;

import com.example.coffeebe.domain.entities.author.User;
import com.example.coffeebe.domain.repositories.RoleRepository;
import com.example.coffeebe.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RoleRepository roleRepository;

    public User getUser(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new Exception("User not exist");
        return user;
    }

}