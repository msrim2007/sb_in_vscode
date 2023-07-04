package com.example.demo.comm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.comm.util.AuthFailureHandler;
import com.example.demo.comm.util.AuthSucsessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private AuthSucsessHandler authSucsessHandler;

    @Autowired
    private AuthFailureHandler authFailureHandler;

    @Bean
    public BCryptPasswordEncoder encryptPassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((c) -> c.disable());
        http.authorizeHttpRequests((ahr) -> ahr.anyRequest().permitAll());
        http.formLogin((fl) -> fl
            .usernameParameter("USER_ID")
            .passwordParameter("USER_PW")
            .loginPage("/user/login.do")
            .loginProcessingUrl("/user/Ajax/loginRequest.do")
            .successHandler(authSucsessHandler)
            .failureHandler(authFailureHandler)
        );
        http.logout((lo) -> lo
            .logoutRequestMatcher(new AntPathRequestMatcher("/user/Ajax/logout.do"))
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID", "remember-me")
        );
        http.sessionManagement((sm) -> sm
            .maximumSessions(1)
            .maxSessionsPreventsLogin(true)
            .expiredUrl("/user/login?error=true&exception=이미 접속중인 계정입니다.")
        );
        http.rememberMe((rm) -> rm
            .alwaysRemember(false)
            .tokenValiditySeconds(1800)
            .rememberMeParameter("remember-me")
        );

        return http.build();
    }
}
