package com.example.coffeebe.app.dtos.request.impl;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.domain.entities.business.Category;
import lombok.Data;

@Data
public class CategoryDto implements DTO<Category> {

    private Long id;

    private String name;

    private String link;

    private Integer position;

    private Long parent_id;
}
