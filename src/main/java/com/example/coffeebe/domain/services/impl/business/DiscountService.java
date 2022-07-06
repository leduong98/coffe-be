package com.example.coffeebe.domain.services.impl.business;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.app.dtos.request.FilterDto;
import com.example.coffeebe.app.dtos.request.impl.DiscountDto;
import com.example.coffeebe.app.dtos.request.impl.DiscountFilterDto;
import com.example.coffeebe.app.dtos.responses.CustomPage;
import com.example.coffeebe.app.dtos.responses.DiscountResponse;
import com.example.coffeebe.domain.entities.business.Discount;
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
import java.util.stream.Collectors;

@Service
@Log4j2
public class DiscountService extends BaseAbtractService implements BaseService<Discount, Long> {
    @Override
    public CustomPage<Discount> findAll(Pageable pageable) {
        Page<Discount> discountPage = discountRepository.findAll(pageable);
        return new CustomPage<>(discountPage);
    }

    public CustomPage<DiscountResponse> findAllByFilter(DiscountFilterDto filterDto, Pageable pageable){
        Page<Discount> discountPage = discountRepository.findAllByFilter(filterDto, pageable);
        CustomPage<DiscountResponse> discountResponseCustomPage = new CustomPage<>();
        discountResponseCustomPage.setData(discountPage.getContent().stream().map(ele -> modelMapper.map(ele, DiscountResponse.class)).collect(Collectors.toList()));
        discountResponseCustomPage.setMetadata(new CustomPage.Metadata(discountPage));

        return discountResponseCustomPage;
    }

    @Override
    public Discount findById(HttpServletRequest request, Long id) {
        return getDiscountById(id);
    }

    @Override
    public Discount create(HttpServletRequest request, DTO dto) {
        DiscountDto discountDto = modelMapper.map(dto, DiscountDto.class);
        if (discountDto.getStartDate().getTime() >= discountDto.getEndDate().getTime()){
            throw new CustomException(HttpStatus.BAD_REQUEST, CustomErrorMessage.TIME_INVALID);
        }
        Discount discount = Discount.builder()
                .product(getProductById(discountDto.getProductId()))
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
