package com.example.demo.web.controller.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/notice/")
public class NoticeController {
    
    @RequestMapping("noticeList.do")
    public String noticeList(HttpServletRequest request, HttpServletResponse response) {
        return "views/noticeList";
    }
}
