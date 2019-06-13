package com.realserver.authentication;

import com.realserver.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Map;

public class AuthenticationTokenProcessingFilter extends GenericFilterBean {

    @Autowired
    UserService userService;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Map<String, String[]> requestParams = servletRequest.getParameterMap();
        if(requestParams.containsKey("token")){

        }
    }
}
