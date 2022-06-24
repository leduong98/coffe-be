package com.example.coffeebe.domain.services.impl.business;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.app.dtos.request.FilterDto;
import com.example.coffeebe.app.dtos.request.impl.CategoryDto;
import com.example.coffeebe.domain.entities.business.Category;
import com.example.coffeebe.domain.services.BaseService;

import com.example.coffeebe.domain.services.impl.BaseAbtractService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Service
@Log4j2
public class CategoryService extends BaseAbtractService implements BaseService<Category, Long> {

    @Override
    public Page<Category> findAll(Pageable pageable) throws Exception {
        Page<Category> categories = categoryRepository.findAll(pageable);
        if (categories.isEmpty()) {
            throw new Exception("Category is not find");
        }
        return categories;
    }

    @Override
    public Category findById(HttpServletRequest request, Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Error: Category is not found.")
        );
        return category;
    }

    @Override
    public Category create(HttpServletRequest request, DTO dto) {
        CategoryDto categoryDto = modelMapper.map(dto, CategoryDto.class);
        Category category = Category.builder().link(categoryDto.getLink())
                .name(categoryDto.getName()).position(categoryDto.getPosition())
                .parentId(categoryDto.getParentId()).build();
        return categoryRepository.save(category);
    }

    @Override
    public Category update(HttpServletRequest request, Long id, DTO dto) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Error: Category is not found.")
        );
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
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Error: Category is not found.")
        );

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