package com.example.coffeebe.app.dtos.responses;

import com.example.coffeebe.domain.entities.business.Product;
import com.example.coffeebe.domain.entities.business.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long id;

    private Integer quantity;

    private Double amount;

    private String data;

    private String status;

    private Product product;

    private Transaction transaction;
}
