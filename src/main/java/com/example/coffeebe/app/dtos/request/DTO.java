package com.example.coffeebe.app.dtos.request;


import com.example.coffeebe.app.dtos.request.impl.CategoryDto;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
        property = "json_type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = CategoryDto.class, name = "category"),
})
public interface DTO<O> {}
