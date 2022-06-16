package com.example.coffeebe.domain.services;

import com.example.coffeebe.domain.entities.author.Role;
import com.example.coffeebe.domain.entities.enums.RoleType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService extends BaseService {

    public Role findByRoleType(RoleType roleType){
        return roleRepository.findByRoleType(roleType);
    }

    public void initRole(){
        List<Role> roles = new ArrayList();
        roles.add(Role.builder().id(generateSequence(Role.SEQUENCE_NAME)).roleType(RoleType.USER).build());
        roles.add(Role.builder().id(generateSequence(Role.SEQUENCE_NAME)).roleType(RoleType.ADMIN).build());
        roles.add(Role.builder().id(generateSequence(Role.SEQUENCE_NAME)).roleType(RoleType.EMPLOYEE).build());
        roleRepository.saveAll(roles);
    }
}
