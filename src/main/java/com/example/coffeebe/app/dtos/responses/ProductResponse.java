package com.example.coffeebe.app.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;

    private String name;

    private String detail;

    private Double price;

    private String image;

    private CategoryResponse category;

    private String imageList;

    private Integer quantity;
}
