package com.example.coffeebe.app.dtos.request.impl;

import com.example.coffeebe.app.dtos.request.FilterDto;
import com.example.coffeebe.domain.entities.business.Product;
import lombok.Data;

@Data
public class ProductFilterDto implements FilterDto<Product> {

    private Long categoryId;

}
