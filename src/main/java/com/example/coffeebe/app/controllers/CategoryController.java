package com.example.coffeebe.app.controllers;

import com.example.coffeebe.app.dtos.request.impl.CategoryFilterDto;
import com.example.coffeebe.app.dtos.responses.CategoryResponse;
import com.example.coffeebe.domain.entities.business.Category;
import com.example.coffeebe.domain.services.impl.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/category")
public class CategoryController extends BaseController<Category, Long, CategoryResponse, CategoryFilterDto>{
	@Autowired
	CategoryService categoryService;

    public CategoryController() {
        super(CategoryResponse.class, CategoryFilterDto.class);
    }

}
