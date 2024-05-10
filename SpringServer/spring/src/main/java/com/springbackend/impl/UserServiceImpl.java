package com.springbackend.impl;

import com.springbackend.entity.User;
import com.springbackend.repository.UserRepository;
import com.springbackend.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.delete(getUser(userId));
    }

}
