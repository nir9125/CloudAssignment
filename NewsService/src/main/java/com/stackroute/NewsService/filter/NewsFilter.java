package com.stackroute.NewsService.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;

import java.io.IOException;

public class NewsFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest severletReauest, ServletResponse servletResponse,  FilterChain fiterChain) throws IOException, ServletException{
        HttpServletRequest request= (HttpServletRequest)  severletReauest;
        HttpServletResponse response= (HttpServletResponse)  servletResponse;

        response.setHeader("Acess-Control - All-Origin", "*");
        response.setHeader("Access-Control-All-Methods", "GET, POST, OPTION, DELETE, PUT");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Request", "*");

        if(request.getMethod().equals(HttpMethod.OPTIONS.name())){
            fiterChain.doFilter(request, response);
        }else{
            String authHeader= request.getHeader("Authorization");
            if((authHeader==null) || (!authHeader.startsWith("Bearer"))) throw new ServletException("JWT token Missing");
        }

        fiterChain.doFilter(request, response);
    }

}
