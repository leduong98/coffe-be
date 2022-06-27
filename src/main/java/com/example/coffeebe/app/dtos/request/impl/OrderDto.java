package com.example.coffeebe.app.dtos.request.impl;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.domain.entities.business.Order;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonTypeName("order")
public class OrderDto implements DTO<Order> {

    private Long id;

    @NotNull(message = "quantity not null")
    private Integer quantity;

    @NotNull(message = "amount not null")
    private Double amount;

    @NotNull(message = "data not null ")
    private String data;

    @NotNull(message = "status not null")
    private String status;

    @NotNull(message = "product_id not null")
    @JsonProperty("product_id ")
    private Long productID;

    @NotNull(message = "transaction_id not null")
    @JsonProperty("transaction_id")
    private Long transactionId;
}
