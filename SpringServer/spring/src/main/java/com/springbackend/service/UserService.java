package com.springbackend.service;

import com.springbackend.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void saveUser(User user);
    User getUser(String userId);
    void deleteUser(String userId);

}
