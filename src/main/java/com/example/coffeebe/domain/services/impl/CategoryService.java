package com.example.coffeebe.domain.services.impl;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.app.dtos.request.FilterDto;
import com.example.coffeebe.app.dtos.request.impl.CategoryDto;
import com.example.coffeebe.domain.entities.business.Category;
import com.example.coffeebe.domain.services.BaseService;

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
	public Page<Category> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category findById(HttpServletRequest request, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category create(HttpServletRequest request, DTO dto) {
		CategoryDto categoryDto = modelMapper.map(dto, CategoryDto.class);
		Category category = Category.builder().link(categoryDto.getLink())
				.name(categoryDto.getName()).position(categoryDto.getPosition())
				.parentId(categoryDto.getParent_id()).build();
		return categoryRepository.save(category);
	}

	@Override
	public Category update(HttpServletRequest request, Long id, DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(HttpServletRequest request, Long id) {
		// TODO Auto-generated method stub
		return false;
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