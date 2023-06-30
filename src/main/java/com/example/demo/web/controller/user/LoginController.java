package com.example.demo.web.controller.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.comm.util.UserAuthCd;
import com.example.demo.web.dto.UserDTO;
import com.example.demo.web.service.UserService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user/")
public class LoginController {
    
    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping("signUp.do")
    public String signUp(HttpServletRequest request, HttpServletResponse response) {
        return "/views/user/signup.html";
    }

    @RequestMapping("Ajax/signUpRequest.do")
    @ResponseBody
    public Map<String, String> signUpRequest(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO) {
        Map<String, String> resultMap = new HashMap<>();

        // USER VALIDATE
        Map<String, String> validResultMap = userSignUpValidate(userDTO);
        if (!validResultMap.get("result").equals("succ"))
            return setMapResultAndMsg(resultMap, "fail", validResultMap.get("msg"));

        // SET USER AUTH CD TO REGULAR
        userDTO.setUSER_AUTH_CD(UserAuthCd.REGULAR.getCode());

        // ISERT USER
        if (userService.insertUser(userDTO) == 1) 
            return setMapResultAndMsg(resultMap, "succ", validResultMap.get("회원가입 성공"));
        else return setMapResultAndMsg(resultMap, "fail", validResultMap.get("회원가입 실패"));
    }

    private Map<String, String> userSignUpValidate(UserDTO userDTO) {
        Map<String, String> resultMap = new HashMap<>();

        // ID DUPLICATE CHECK
        if (userService.selectUserForId(userDTO.getUSER_ID()) != null) 
            return setMapResultAndMsg(resultMap, "fail", "이미 사용중인 아이디입니다.");
        else return setMapResultAndMsg(resultMap, "succ", "사용 가능한 아이디입니다.");
    }

    private Map<String, String> setMapResultAndMsg(Map<String, String> map, String result, String msg) {
        if (map == null) map = new HashMap<>();
        map.put("result", result);
        map.put("msg", msg);
        return map;
    }
}
