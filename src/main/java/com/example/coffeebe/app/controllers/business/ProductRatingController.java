package com.example.coffeebe.app.controllers.business;

import com.example.coffeebe.app.controllers.BaseController;
import com.example.coffeebe.app.dtos.request.impl.ProductRatingFilterDto;
import com.example.coffeebe.app.dtos.responses.ProductRatingResponse;
import com.example.coffeebe.domain.entities.business.ProductRating;
import com.example.coffeebe.domain.services.impl.business.ProductRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rating")
public class ProductRatingController extends BaseController<ProductRating, Long, ProductRatingResponse, ProductRatingFilterDto> {

    @Autowired
    ProductRatingService productRatingService;

    protected ProductRatingController() {
        super(ProductRatingResponse.class, ProductRatingFilterDto.class);
    }
}
