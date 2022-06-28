package com.example.coffeebe.domain.services.impl.author;

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
import com.example.coffeebe.domain.services.impl.BaseAbtractService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Log4j2
public class UserService extends BaseAbtractService implements BaseService<User, Long> {

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;

    //findById
    public UserDetails loadUserById(long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("not found");
        }
        return new CustomUserDetails(user);
    }

    //register
    public ResponseEntity<?> register(RegisterRequest registerRequest) {
        boolean emailExits = userRepository.existsByEmail(registerRequest.getEmail());
        if (emailExits) {
            return new ResponseEntity<>("Email have existed!", HttpStatus.BAD_REQUEST);
        }

        Role userRole =roleRepository.findByName(RoleType.USER)
                    .orElseThrow(
                            () -> new RuntimeException("Error: Role is not found.")
                    );

        User user = User.builder()
                .email(registerRequest.getEmail())
                .fullName(registerRequest.getFullname())
                .phoneNumber(registerRequest.getPhone())
                .password(encoder.encode(registerRequest.getPassword()))
                .birthday(registerRequest.getBirthday())
                .address(registerRequest.getAddress())
                .role(userRole)
                .status(Status.ACTIVE)
                .build();
        userRepository.save(user);
        return new ResponseEntity<>("Register your account success!", HttpStatus.OK);
    }

    //login
    public ResponseEntity<?> login(LoginRequest loginRequest) throws Exception {
        User user = getUserByEmail(loginRequest.getEmail());
        boolean checkAccount = encoder.matches(loginRequest.getPassword(), user.getPassword());
        if (!checkAccount) {
            return new ResponseEntity<>("Wrong password", HttpStatus.BAD_REQUEST);
        }


        if (user.getStatus().equals(Status.NON_ACTIVE)) {
            return new ResponseEntity<>("your account is not active", HttpStatus.BAD_REQUEST);
        } else {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
            return ResponseEntity.ok(new LoginResponse(jwt));
        }
    }

    @Override
    public Page<User> findAll() throws Exception {
        return null;
    }

    @Override
    public User findById(HttpServletRequest request, Long id) {
        return getById(id);
    }

    @Override
    public User create(HttpServletRequest request, DTO dto) {
        return null;
    }

    @Override
    public User update(HttpServletRequest request, Long id, DTO dto) {
        return null;
    }

    @Override
    public boolean delete(HttpServletRequest request, Long id) {
        return false;
    }

    @Override
    public Page<User> filter(FilterDto<User> dto, Pageable pageable) {
        return null;
    }

    @Override
    public List<User> filter(HttpServletRequest request) {
        return null;
    }
}