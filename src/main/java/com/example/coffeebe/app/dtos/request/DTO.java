package com.example.coffeebe.app.dtos.request;


import com.example.coffeebe.app.dtos.request.impl.*;
import com.example.coffeebe.domain.entities.business.Discount;
import com.example.coffeebe.domain.entities.business.Order;
import com.example.coffeebe.domain.entities.business.Slider;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
        property = "json_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CategoryDto.class, name = "category"),
        @JsonSubTypes.Type(value = ProductDto.class, name = "product"),
        @JsonSubTypes.Type(value = DiscountDto.class, name = "discount"),
        @JsonSubTypes.Type(value = SliderDto.class, name = "slider"),
        @JsonSubTypes.Type(value = OrderDto.class, name = "order"),

})
public interface DTO<O> {}
