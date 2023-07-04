package com.example.demo.web.dto.notice;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class NoticeDTO {
    private int NOTICE_SEQNO;
    private String NOTICE_TITLE;
    private String NOTICE_CONTENT;
    private int NOTICE_VIEW_CNT;
    private int NOTICE_LIKE_CNT;
    private String NOTICE_REG_DTTM;
    private String NOTICE_REG_USER_ID;
}
