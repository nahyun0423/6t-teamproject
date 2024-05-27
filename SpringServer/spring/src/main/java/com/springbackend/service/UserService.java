package com.springbackend.service;

import com.springbackend.dto.UserDTO;
import com.springbackend.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    
    UserDTO signUp(UserDTO userDTO);
    UserDTO login(String userId,String password);
    UserDTO edit(UserDTO userDTO);
    String checkDup(UserDTO userDTO);


}
