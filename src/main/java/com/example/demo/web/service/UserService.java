package com.example.demo.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.web.dto.user.UserDTO;
import com.example.demo.web.mapper.UserMapper;

@Service("userService")
public class UserService implements UserDetailsService {
    
    @Autowired
    private UserMapper userMapper;

    public int insertUser(UserDTO userDTO) {
        return userMapper.insertUser(userDTO);
    }

    public UserDTO selectUserForId(String USER_ID) {
        return userMapper.selectUserForId(USER_ID);
    }

    public UserDTO selectUserForLogin(UserDTO userDTO) {
        return userMapper.selectUserForLogin(userDTO);
    }

    @Override
    public UserDetails loadUserByUsername(String USER_ID) throws UsernameNotFoundException {
        UserDetails user = userMapper.selectUserForId(USER_ID);
        if (user == null) throw new UsernameNotFoundException("계정 정보를 찾기 못했습니다.");
        return user;
    }
} 
