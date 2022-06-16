package com.example.coffeebe.domain.services;

import com.example.coffeebe.app.dtos.request.LoginRequest;
import com.example.coffeebe.app.dtos.request.RegisterRequest;
import com.example.coffeebe.app.dtos.response.LoginResponse;
import com.example.coffeebe.domain.configs.jwt.JwtTokenProvider;
import com.example.coffeebe.domain.entities.CustomUserDetails;
import com.example.coffeebe.domain.entities.author.Role;
import com.example.coffeebe.domain.entities.author.User;
import com.example.coffeebe.domain.entities.enums.RoleType;
import com.example.coffeebe.domain.entities.enums.UserStatus;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service
@Log4j2
public class UserService extends BaseService {

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;



    //findById
    public UserDetails loadUserById(long id){
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            throw new UsernameNotFoundException("not found");
        }
        return new CustomUserDetails(user);
    }

    //register
    public ResponseEntity<?> register(RegisterRequest registerRequest){
        boolean emailExits = userRepository.existsByEmail(registerRequest.getEmail());
        if (emailExits){
            return new ResponseEntity<>("Email have existed!", HttpStatus.BAD_REQUEST);
        }
        Role role = roleRepository.findByRoleType(RoleType.USER);
        User user = User.builder()
                .id(generateSequence(User.SEQUENCE_NAME))
                .email(registerRequest.getEmail())
                .password(encoder.encode(registerRequest.getPassword()))
                .role(role)
                .userStatus(UserStatus.ACTIVE)
                .build();
        userRepository.save(user);
        return new ResponseEntity<>("Register your account success!", HttpStatus.OK);
    }

    //login
    public ResponseEntity<?> login(LoginRequest loginRequest) throws Exception {
        User user = getUser(loginRequest.getEmail());
        boolean checkAccount = encoder.matches(loginRequest.getPassword(), user.getPassword());
        if (!checkAccount){
            return new ResponseEntity<>("Wrong password", HttpStatus.BAD_REQUEST);
        }


        if (user.getUserStatus().equals(UserStatus.NON_ACTIVE)){
            return new ResponseEntity<>("your account is not active", HttpStatus.BAD_REQUEST);
        }
        else {
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


}