package com.example.demo.comm.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.demo.web.dto.user.UserDTO;

public class UserUtil {
    public static UserDTO getUserDTO() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            if (auth != null) return (UserDTO) auth.getPrincipal();
        } catch(ClassCastException e) {
            System.out.println("어째서?");
            return null;
        }
        return null;
    }
}
