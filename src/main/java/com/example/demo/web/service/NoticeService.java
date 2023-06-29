package com.example.demo.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.web.dto.NoticeDTO;
import com.example.demo.web.mapper.NoticeMapper;

@Service("noticeService")
public class NoticeService {
    
    @Autowired
    private NoticeMapper noticeMapper;

    public List<NoticeDTO> selectAllNoticeList() {
        return noticeMapper.selectAllNoticeList();
    }
}
