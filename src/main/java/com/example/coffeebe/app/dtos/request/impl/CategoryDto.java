package com.example.coffeebe.app.dtos.request.impl;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.domain.entities.business.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

@Data
@JsonTypeName("category")
public class CategoryDto implements DTO<Category> {

    private Long id;

    private String name;

    private String link;

    private Integer position;

    @JsonProperty("parent_id")
    private Long parentId;
}
