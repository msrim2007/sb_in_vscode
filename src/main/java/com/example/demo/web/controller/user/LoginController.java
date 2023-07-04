package com.example.demo.web.controller.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.comm.util.UserAuthCd;
import com.example.demo.web.dto.user.UserDTO;
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

    @RequestMapping("login.do")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        System.out.println(request.getAttribute("exception"));
        System.out.println(request.getParameter("exception"));
        model.addAttribute("err", request.getAttribute("err"));
        model.addAttribute("exception", request.getAttribute("exception"));
        return "/views/user/login.html";
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

        // ENCRYPT PASSWORD
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDTO.setUSER_PW(passwordEncoder.encode(userDTO.getUSER_PW()));

        // ISERT USER
        if (userService.insertUser(userDTO) == 1) 
            return setMapResultAndMsg(resultMap, "succ", "회원가입 성공");
        else return setMapResultAndMsg(resultMap, "fail", "회원가입 실패");
    }

    private Map<String, String> userSignUpValidate(UserDTO userDTO) {
        Map<String, String> resultMap = new HashMap<>();

        // EMPTY CHECK
        if (userDTO.getUSER_NM().isEmpty()) return setMapResultAndMsg(resultMap, "fail", "이름을 확인해주세요.");
        if (userDTO.getUSER_ID().isEmpty()) return setMapResultAndMsg(resultMap, "fail", "아이디를 확인해주세요.");
        if (userDTO.getUSER_PW().isEmpty()) return setMapResultAndMsg(resultMap, "fail", "비밀번호를 확인해주세요.");

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
