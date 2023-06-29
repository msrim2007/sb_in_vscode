package com.example.demo.comm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.comm.interceptor.UserInfoInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry reg) {
        reg.addInterceptor(new UserInfoInterceptor())
            .addPathPatterns("/")
            .addPathPatterns("/notice/**");
    }
}
