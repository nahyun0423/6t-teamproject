package com.springbackend.controller;

import com.springbackend.dto.UserDTO;
import com.springbackend.entity.User;

import com.springbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //DB에 회원정보 저장(회원가입)
    @PostMapping("/users/save")
    public void saveUser(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
    }

    //ID로 DB에서 유저 검색(로그인)
    @GetMapping("/users/userInfo/{userId}")
    public UserDTO userInfo(@PathVariable String userId){
        return userService.getUser(userId);
    }
}
