package com.example.coffeebe.app.dtos.request.impl;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.domain.entities.business.Discount;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@JsonTypeName("discount")
public class DiscountDto implements DTO<Discount> {

    private Long id;

    @NotNull(message = "productId not null")
    @JsonProperty("product_id")
    private Long productId;

    @NotNull(message = "startDate not null")
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date startDate;

    @NotNull(message = "endDate not null")
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date endDate;

    @NotNull(message = "discount not null")
    private Integer discount;
}
