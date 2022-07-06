package com.example.coffeebe.domain.services.impl.business;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.app.dtos.request.FilterDto;
import com.example.coffeebe.app.dtos.request.impl.CategoryDto;
import com.example.coffeebe.app.dtos.responses.CustomPage;
import com.example.coffeebe.domain.entities.business.Category;
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
public class CategoryService extends BaseAbtractService implements BaseService<Category, Long> {

    @Override
    public CustomPage<Category> findAll(Pageable pageable) {
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return new CustomPage<>(categoryPage);
    }

    @Override
    public Category findById(HttpServletRequest request, Long id) {
        return getCategoryById(id);
    }

    @Override
    public Category create(HttpServletRequest request, DTO dto) {
        CategoryDto categoryDto = modelMapper.map(dto, CategoryDto.class);
        if (categoryRepository.existsByName(categoryDto.getName())){
            throw new CustomException(HttpStatus.BAD_REQUEST, CustomErrorMessage.NAME_EXISTS);
        }
        Category category = Category.builder()
                .name(categoryDto.getName())
                .position(categoryDto.getPosition())
                .productDetail(categoryDto.getProductDetail())
                .status(true)
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public Category update(HttpServletRequest request, Long id, DTO dto) {
        Category category = findById(request, id);
        CategoryDto categoryDto = modelMapper.map(dto, CategoryDto.class);
        category.setName(categoryDto.getName());
        category.setPosition(categoryDto.getPosition());
        category.setProductDetail(categoryDto.getProductDetail());

        categoryRepository.save(category);
        return category;
    }

    @Override
    public boolean delete(HttpServletRequest request, Long id) {
        Category category = findById(request, id);
        category.setStatus(false);
        categoryRepository.save(category);
        return true;
    }

    @Override
    public Page<Category> findAllByFilter(FilterDto<Category> dto, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Category> findAllByFilter(HttpServletRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

}