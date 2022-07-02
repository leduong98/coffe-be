package com.example.coffeebe.app.dtos.responses;

import com.example.coffeebe.domain.entities.business.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountResponse {

    private Long id;

    private ProductResponse product;

    private Date startDate;

    private Date endDate;

    private Integer discount;
}
