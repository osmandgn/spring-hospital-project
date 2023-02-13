package com.runners.service;


import com.runners.domain.Role;
import com.runners.domain.User;
import com.runners.domain.enums.UserRole;
import com.runners.dto.UserDto;
import com.runners.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;


    public void saveUser(UserDto userDto) {

        User user = new User();

        user.setFirstName(userDto.getFirstName());
        user.setUserName(userDto.getUserName());

        String pwrd = userDto.getPassword();
        String encodePwrd = passwordEncoder.encode(pwrd);

        user.setPassword(encodePwrd);

        Role role = roleService.getByRole(UserRole.ROLE_ADMIN);

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);


    }
}
