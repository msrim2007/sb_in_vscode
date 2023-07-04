package com.example.demo.comm.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.demo.web.dto.user.UserDTO;

public class UserUtil {
    public static UserDTO getUserDTO() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try { // TODO : 로그인 전에 접근 시 CastException 나오는 이유 찾기
            if (auth != null) return (UserDTO) auth.getPrincipal();
        } catch(Exception e) {
            return null;
        }
        return null;
    }
}
