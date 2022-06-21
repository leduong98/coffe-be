package com.example.coffeebe.domain.services.impl;

import com.example.coffeebe.domain.entities.author.User;
import com.example.coffeebe.domain.repositories.CategoryRepository;
import com.example.coffeebe.domain.repositories.RoleRepository;
import com.example.coffeebe.domain.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseAbtractService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    public CategoryRepository categoryRepository;

    @Autowired
    public ModelMapper modelMapper;

    public User getUser(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new Exception("User not exist");
        return user;
    }

}