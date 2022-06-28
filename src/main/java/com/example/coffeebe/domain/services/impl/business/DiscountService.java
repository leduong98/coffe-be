package com.example.coffeebe.domain.services.impl.business;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.app.dtos.request.FilterDto;
import com.example.coffeebe.app.dtos.request.impl.DiscountDto;
import com.example.coffeebe.domain.entities.business.Discount;
import com.example.coffeebe.domain.entities.business.Product;
import com.example.coffeebe.domain.services.BaseService;
import com.example.coffeebe.domain.services.impl.BaseAbtractService;
import com.example.coffeebe.domain.utils.exception.CustomErrorMessage;
import com.example.coffeebe.domain.utils.exception.CustomException;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Log4j2
public class DiscountService extends BaseAbtractService implements BaseService<Discount, Long> {
    @Override
    public Page<Discount> findAll() throws Exception {
        Page<Discount> discounts = discountRepository.findAll(pageable);
        if (discounts.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, CustomErrorMessage.DISCOUNT_NOT_FOUND);
        }
        return discounts;
    }

    @Override
    public Discount findById(HttpServletRequest request, Long id) {
        return discountRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, CustomErrorMessage.DISCOUNT_NOT_FOUND)
        );
    }

    @Override
    public Discount create(HttpServletRequest request, DTO dto) {
        DiscountDto discountDto = modelMapper.map(dto, DiscountDto.class);
        Product product1 = productRepository.findById(discountDto.getProductId()).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, CustomErrorMessage.PRODUCT_NOT_FOUND));
        Discount discount = Discount.builder()
                .product(product1)
                .startDate(discountDto.getStartDate())
                .endDate(discountDto.getEndDate())
                .discount(discountDto.getDiscount())
                .build();
        return discountRepository.save(discount);
    }

    @Override
    public Discount update(HttpServletRequest request, Long id, DTO dto) {
        Discount discount = findById(request,id);
        DiscountDto discountDto = modelMapper.map(dto, DiscountDto.class);
        discount.setProduct(getProductById(discountDto.getProductId()));
        discount.setStartDate(discountDto.getStartDate());
        discount.setEndDate(discountDto.getEndDate());
        discount.setDiscount(discountDto.getDiscount());
        return discountRepository.save(discount);
    }

    @Override
    public boolean delete(HttpServletRequest request, Long id) {
        Discount discount = findById(request, id);
        discountRepository.delete(discount);
        return true;
    }

    @Override
    public Page<Discount> filter(FilterDto<Discount> dto, Pageable pageable) {
        return null;
    }

    @Override
    public List<Discount> filter(HttpServletRequest request) {
        return null;
    }
}
