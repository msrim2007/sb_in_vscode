package com.example.demo.comm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.comm.util.UserUtil;
import com.example.demo.web.dto.user.UserDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserInfoInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession(false);
        UserDTO user = UserUtil.getUserDTO();
        if (session == null || user == null) request.setAttribute("_USER_DTO", null);
        else request.setAttribute("_USER_DTO", user);
        return true;
    }
    
}
