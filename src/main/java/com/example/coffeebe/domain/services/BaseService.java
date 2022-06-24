package com.example.coffeebe.domain.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.app.dtos.request.FilterDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface BaseService<T, S> {
  Page<T> findAll(Pageable pageable) throws Exception;

  T findById(HttpServletRequest request, S id);

  @Transactional(rollbackFor = Exception.class)
  T create(HttpServletRequest request, DTO dto);

  @Transactional(rollbackFor = Exception.class)
  T update(HttpServletRequest request, S id, DTO dto);

  T update(HttpServletRequest request, Long id, DTO dto);

  @Transactional(rollbackFor = Exception.class)
  boolean delete(HttpServletRequest request, S id);

  Page<T> filter(FilterDto<T> dto, Pageable pageable);

  List<T> filter(HttpServletRequest request);
}
