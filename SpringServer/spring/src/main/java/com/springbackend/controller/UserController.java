package com.springbackend.controller;

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

    @PostMapping("/users/save")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @GetMapping("/users/userInfo/{userId}")
    public User userInfo(@PathVariable String userId){
        return userService.getUser(userId);
    }


}
