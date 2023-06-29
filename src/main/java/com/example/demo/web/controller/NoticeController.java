package com.example.demo.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.web.service.NoticeService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/notice/")
public class NoticeController {

    @Resource(name = "noticeService")
    NoticeService noticeService;

    @RequestMapping("noticeList.do")
    public ModelAndView noticeList(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<>();
        model.put("noticeList", noticeService.selectAllNoticeList());
        return new ModelAndView("views/noticeList", model);
    }
}
