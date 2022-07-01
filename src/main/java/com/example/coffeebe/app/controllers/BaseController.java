package com.example.coffeebe.app.controllers;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.app.dtos.request.FilterDto;
import com.example.coffeebe.app.dtos.responses.CustomPage;
import com.example.coffeebe.domain.services.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public abstract class BaseController<O, ID, P1, FD extends FilterDto<O>> {
    final Class<P1> responseClass;
    final Class<FD> filterDto;
    final ModelMapper modelMapper;
    @Autowired
    BaseService<O, ID> service;

    protected BaseController(Class<P1> responseClass, Class<FD> fpClass) {
        this.responseClass = responseClass;
        this.filterDto = fpClass;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/all")
    CustomPage<O> findAll(Pageable pageable) {
        CustomPage<O> page = service.findAll(pageable);
        return page;
    }

    @GetMapping("/{id}")
    P1 getById(HttpServletRequest request, @PathVariable ID id) {
        O ob = service.findById(request, id);
        return modelMapper.map(ob, responseClass);
    }

    @PostMapping()
    P1 create(HttpServletRequest request, @Valid @RequestBody DTO dto) {
        O ob = service.create(request, dto);
        return modelMapper.map(ob, responseClass);
    }

    @PatchMapping("/{id}")
    P1 update(HttpServletRequest request, @PathVariable ID id, @Valid @RequestBody DTO dto) {
        O ob = service.update(request, id, dto);
        return modelMapper.map(ob, responseClass);
    }

    @DeleteMapping("/{id}")
    boolean delete(HttpServletRequest request, @PathVariable ID id) {
        return service.delete(request, id);
    }

    @GetMapping()
    Page<O> filter(FD dto, Pageable pageable) {
        Page<O> page = service.filter(dto, pageable);
        return page;
    }
}
