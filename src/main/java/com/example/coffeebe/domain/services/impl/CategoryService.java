package com.example.coffeebe.domain.services.impl;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.app.dtos.request.FilterDto;
import com.example.coffeebe.app.dtos.request.LoginRequest;
import com.example.coffeebe.app.dtos.request.RegisterRequest;
import com.example.coffeebe.app.dtos.responses.LoginResponse;
import com.example.coffeebe.domain.configs.jwt.JwtTokenProvider;
import com.example.coffeebe.domain.entities.CustomUserDetails;
import com.example.coffeebe.domain.entities.author.Role;
import com.example.coffeebe.domain.entities.author.User;
import com.example.coffeebe.domain.entities.business.Category;
import com.example.coffeebe.domain.entities.enums.RoleType;
import com.example.coffeebe.domain.entities.enums.Status;
import com.example.coffeebe.domain.services.BaseService;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		// TODO Auto-generated method stub
		return null;
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