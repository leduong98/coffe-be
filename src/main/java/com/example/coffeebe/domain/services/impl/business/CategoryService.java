package com.example.coffeebe.domain.services.impl.business;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.app.dtos.request.FilterDto;
import com.example.coffeebe.app.dtos.request.impl.CategoryDto;
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
    public Page<Category> findAll() {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories;
    }

    @Override
    public Category findById(HttpServletRequest request, Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, CustomErrorMessage.CATEGORY_NOT_FOUND));
    }

    @Override
    public Category create(HttpServletRequest request, DTO dto) {
        CategoryDto categoryDto = modelMapper.map(dto, CategoryDto.class);
        Category category = Category.builder()
                .link(categoryDto.getLink())
                .name(categoryDto.getName())
                .position(categoryDto.getPosition())
                .parentId(categoryDto.getParentId())
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public Category update(HttpServletRequest request, Long id, DTO dto) {
        Category category = findById(request, id);
        CategoryDto categoryDto = modelMapper.map(dto, CategoryDto.class);
        category.setName(categoryDto.getName());
        category.setLink(categoryDto.getLink());
        category.setPosition(categoryDto.getPosition());
        category.setParentId(categoryDto.getParentId());

        categoryRepository.save(category);
        return category;
    }

    @Override
    public boolean delete(HttpServletRequest request, Long id) {
        Category category = findById(request, id);

        categoryRepository.delete(category);
        return true;
    }

    @Override
    public Page<Category> filter(FilterDto<Category> dto, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Category> filter(HttpServletRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

}