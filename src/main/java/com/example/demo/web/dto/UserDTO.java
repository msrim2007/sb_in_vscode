package com.example.demo.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class UserDTO {
    private String USER_ID;
    private String USER_PW;
    private String USER_NM;
    private String USER_AUTH_CD;
}
