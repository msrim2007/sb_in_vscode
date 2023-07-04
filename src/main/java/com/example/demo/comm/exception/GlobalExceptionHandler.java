package com.example.demo.comm.exception;

import java.io.IOException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    private final String ERROR_PAGE = "/error.do";

    @ExceptionHandler(Exception.class)
    public void handleBasicException(HttpServletRequest request, HttpServletResponse response, Exception e) throws ServletException, IOException {
        request.setAttribute("message", e.getMessage());
        request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public void handleUsernameNotFoundException(HttpServletRequest request, HttpServletResponse response, UsernameNotFoundException e) throws Exception {
        throw new Exception(e.getMessage());
    }
}
