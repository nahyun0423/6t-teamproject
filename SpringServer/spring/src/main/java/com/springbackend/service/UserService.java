package com.springbackend.service;

import com.springbackend.dto.UserDTO;
import com.springbackend.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void save(UserDTO userDTO);
    UserDTO getUser(String userId);


}
