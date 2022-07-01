package com.example.coffeebe.app.dtos.responses;

import com.example.coffeebe.domain.entities.business.Discount;
import com.example.coffeebe.domain.entities.business.Product;
import com.example.coffeebe.domain.entities.business.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Integer quantity;

    private Product product;

    private Discount discount;
}
