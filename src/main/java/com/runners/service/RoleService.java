package com.runners.service;


import com.runners.domain.Role;
import com.runners.domain.enums.UserRole;
import com.runners.exception.ResourceNotFoundException;
import com.runners.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getByRole(UserRole type) {

        Role role = roleRepository.findByName(type).orElseThrow(
                ()-> new ResourceNotFoundException("Role not found !"));

        return role;

    }


}
