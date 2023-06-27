package com.example.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MainController {
    
    @RequestMapping("/")
    public String index() {
        return "/index";
    }

    @RequestMapping("/err")
    public void errorTest() throws Exception {
        throw new Exception("i am error");
    }

    @RequestMapping("/error.do")
    public String exceptionPage(HttpServletRequest request, HttpServletResponse response) {
        return "/error";
    }
}
