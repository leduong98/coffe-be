package com.example.coffeebe.domain.services;

import com.example.coffeebe.domain.entities.author.Role;
import com.example.coffeebe.domain.entities.enums.RoleType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService extends BaseService {

    public Role findByRoleType(RoleType roleType){
        return roleRepository.findByName(roleType).get();
    }

    public void initRole(){
        List<Role> roles = new ArrayList();
        roles.add(Role.builder().name(RoleType.USER).build());
        roles.add(Role.builder().name(RoleType.ADMIN).build());
        roles.add(Role.builder().name(RoleType.EMPLOYEE).build());
        roleRepository.saveAll(roles);
    }
}
