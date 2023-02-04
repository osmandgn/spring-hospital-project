package com.runners.security.service;

import com.runners.domain.Role;
import com.runners.domain.User;
import com.runners.exception.ResourceNotFoundException;
import com.runners.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

                  User user = userRepository.findByUserName(username).orElseThrow(
                          ()-> new ResourceNotFoundException("User not found with username :"+ username));

              if(user!=null){

                  return new org.
                          springframework.
                          security.
                          core.
                          userdetails.
                          User(user.getUserName(),user.getPassword(),buildGrantedAuthorities(user.getRoles()));
              }else {
                  throw new UsernameNotFoundException("User not found with : "+username);
              }


    }

    private static List<SimpleGrantedAuthority> buildGrantedAuthorities(final Set<Role> roles){

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role w: roles){
            authorities.add(new SimpleGrantedAuthority(w.getName().name()));
        }
        return authorities;

    }






}
