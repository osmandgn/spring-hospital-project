package com.runners.controller;


import com.runners.dto.UserDto;
import com.runners.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserDto userDto){

        userService.saveUser(userDto);

        String message = "User is created successfully";

        return ResponseEntity.ok(message);

    }















}
