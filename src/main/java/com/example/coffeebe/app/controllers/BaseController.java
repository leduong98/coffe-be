package com.example.coffeebe.app.controllers;

import com.example.coffeebe.domain.services.RoleService;
import com.example.coffeebe.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
public class BaseController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;
}
