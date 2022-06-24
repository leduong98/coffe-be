package com.example.coffeebe.app.controllers.business;

import com.example.coffeebe.app.controllers.BaseController;
import com.example.coffeebe.app.dtos.request.impl.SliderDto;
import com.example.coffeebe.app.dtos.request.impl.SliderFilterDto;
import com.example.coffeebe.app.dtos.responses.SliderResponse;
import com.example.coffeebe.domain.entities.business.Slider;
import com.example.coffeebe.domain.services.impl.business.SliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/slider")
public class SliderController extends BaseController<Slider, Long, SliderResponse, SliderFilterDto> {

    @Autowired
    SliderService sliderService;

    protected SliderController() {
        super(SliderResponse.class, SliderFilterDto.class);
    }
}
