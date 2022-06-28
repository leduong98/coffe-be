package com.example.coffeebe.domain.services.impl;

import com.example.coffeebe.domain.entities.author.User;
import com.example.coffeebe.domain.entities.business.*;
import com.example.coffeebe.domain.repositories.*;
import com.example.coffeebe.domain.utils.exception.CustomErrorMessage;
import com.example.coffeebe.domain.utils.exception.CustomException;
import org.hibernate.resource.transaction.TransactionRequiredForJoinException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BaseAbtractService {

    protected final Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);

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

    @Autowired
    public DiscountRepository discountRepository;

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public TransactionRepository transactionRepository;

    public User getUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new Exception("User not exist");
        return user;
    }

    public User getById(Long id){
        User user = userRepository.findByUserId(id);
        if (user == null)
            throw new CustomException(HttpStatus.NOT_FOUND, CustomErrorMessage.USER_NOT_FOUND);
        return user;
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, CustomErrorMessage.CATEGORY_NOT_FOUND)
        );
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, CustomErrorMessage.PRODUCT_NOT_FOUND)
        );
    }

    public Discount getDiscountById(Long id){
        return discountRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, CustomErrorMessage.DISCOUNT_NOT_FOUND)
        );
    }

    public Slider getSliderById(Long id){
        return sliderRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, CustomErrorMessage.SLIDER_NOT_FOUND)
        );
    }

    public Transaction getTransactionById(Long id){
        return transactionRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, CustomErrorMessage.TRANSACTION_NOT_FOUND)
        );
    }


}