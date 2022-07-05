package com.example.coffeebe.app.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    private List<OrderResponse> orders;

    private String email;

    private String phone;

    private String address;

    private String status;

    private UserResponse user;

    @JsonProperty("payment_info")
    private String paymentInfo;

    private String payment;

    private Long id;


}
