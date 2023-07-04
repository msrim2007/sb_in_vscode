package com.example.demo.comm.util;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler{
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        String msg = "invalid id or password";

        if (exception instanceof DisabledException) msg = "DisabledException account";
        else if (exception instanceof CredentialsExpiredException) msg = "CredentialsExpiredException account";
        else if (exception instanceof BadCredentialsException) msg = "BadCredentialsException account";

        request.setAttribute("error", "true");
        request.setAttribute("exception", msg);
        setDefaultFailureUrl("/user/login.do");

        super.onAuthenticationFailure(request, response, exception);
    }
}
