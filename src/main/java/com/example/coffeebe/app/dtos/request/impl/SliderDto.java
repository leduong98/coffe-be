package com.example.coffeebe.app.dtos.request.impl;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.domain.entities.business.Slider;
import lombok.Data;

@Data
public class SliderDto implements DTO<Slider> {

    private Long id;

    private String name;

    private String link;

    private String status;
}
