package com.example.coffeebe.app.dtos.request.impl;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.app.dtos.request.FilterDto;
import com.example.coffeebe.domain.entities.business.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class TransactionDto implements DTO<Transaction> {

    @NotNull(message = "orders not null")
    @Size(min = 1, message = "orders require at least 1")
    @Valid private List<OrderDto> orders;

    private String email;

    private String phone;

    @NotNull(message = "address not null")
    private String address;

    @JsonProperty("payment_info")
    private String paymentInfo;

    @NotNull(message = "payment not null")
    private String payment;

    @AssertTrue(message = "Email or phone not null")
    protected boolean isValidUserEmailOrPhone() {
        return this.email != null || this.phone != null;
    }

}
