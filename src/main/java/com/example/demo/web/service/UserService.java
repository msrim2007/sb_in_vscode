package com.example.demo.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.web.dto.UserDTO;
import com.example.demo.web.mapper.UserMapper;

@Service("userService")
public class UserService {
    
    @Autowired
    private UserMapper userMapper;

    public int insertUser(UserDTO userDTO) {
        return userMapper.insertUser(userDTO);
    }

    public UserDTO selectUserForId(String userId) {
        return userMapper.selectUserForId(userId);
    }
} 