package com.example.coffebe.app.controllers;

import com.example.coffebe.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
public class BaseController {
    @Autowired
    UserService userService;
}
