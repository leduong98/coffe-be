package com.example.coffeebe.domain.services.impl;

import com.example.coffeebe.domain.entities.author.User;
import com.example.coffeebe.domain.entities.business.Category;
import com.example.coffeebe.domain.repositories.*;
import com.example.coffeebe.domain.utils.exception.CustomErrorMessage;
import com.example.coffeebe.domain.utils.exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public SliderRepository sliderRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public ProductRepository productRepository;

    public User getUser(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new Exception("User not exist");
        return user;
    }

    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> {
            throw new CustomException(HttpStatus.NOT_FOUND, CustomErrorMessage.CATEGORY_NOT_FOUND);
        });
    }

}