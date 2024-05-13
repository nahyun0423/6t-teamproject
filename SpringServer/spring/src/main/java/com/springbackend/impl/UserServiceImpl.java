package com.springbackend.impl;

import com.springbackend.dto.UserDTO;
import com.springbackend.entity.User;
import com.springbackend.repository.UserRepository;
import com.springbackend.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(UserDTO userDTO) {
        User user = userDTO.toEntity();
        userRepository.save(user);
    }

    @Override
    public UserDTO getUser(String userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(UserDTO::new).orElse(null);
        }
}
