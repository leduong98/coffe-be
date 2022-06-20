package com.example.coffeebe.app.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.coffeebe.app.dtos.request.DTO;

public interface Base<O, ID> {
	@GetMapping("/{id}")
	O getById(@PathVariable ID id);

	@PostMapping()
	O create(@Valid @RequestBody DTO dto);

	@PutMapping("/{id}")
	O update(@PathVariable ID id, @Valid @RequestBody DTO dto);

	@DeleteMapping("/{id}")
	boolean delete(@PathVariable ID id);

	@GetMapping()
	List<O> filter(HttpServletRequest request);

	@GetMapping(params = { "page", "size" })
	Page<O> filter(HttpServletRequest request, Pageable pageable);
}
