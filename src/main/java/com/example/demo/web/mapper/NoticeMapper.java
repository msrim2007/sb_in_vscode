package com.example.demo.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.web.dto.notice.NoticeDTO;

@Mapper
public interface NoticeMapper {
    List<NoticeDTO> selectAllNoticeList();
}
