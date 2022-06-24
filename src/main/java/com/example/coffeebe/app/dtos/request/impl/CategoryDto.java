package com.example.coffeebe.app.dtos.request.impl;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.domain.entities.business.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonTypeName("category")
public class CategoryDto implements DTO<Category> {

    private Long id;

    @NotNull(message = "name not null")
    private String name;

    @NotNull(message = "link not null")
    private String link;

    @NotNull(message = "position not null")
    private Integer position;

    @NotNull(message = "parentId not null")
    @JsonProperty("parent_id")
    private Long parentId;
}
