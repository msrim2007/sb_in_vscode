package com.example.demo.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.web.dto.UserDTO;

@Mapper
public interface UserMapper {
    int insertUser(UserDTO userDTO);
    UserDTO selectUserForId(String USER_ID);
    UserDTO selectUserForLogin(UserDTO userDTO);
}
