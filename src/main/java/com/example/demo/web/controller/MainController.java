package com.example.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MainController {
    
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/error.do")
    public String exceptionPage(HttpServletRequest request, HttpServletResponse response) {
        return "error/error";
    }
}
