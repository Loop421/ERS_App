package com.ex.web;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class noLockDoorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        response.setHeader("ACCESS-CONTROL-ALLOW-ORIGIN", "*");
        response.setHeader("ACCESS-CONTROL-ALLOW-METHOD", "GET, POST, PUT, OPTION");
        response.setHeader("ACCESS-CONTROL-ALLOW-HEADERS", "Content-Type");
        response.setHeader("Access-Control-Request-Method", "POST");

        if(request.getMethod().equals("OPTION")) {
            return;
        } else
        {
            filterChain.doFilter(servletRequest,servletResponse);
        }
//        if(servletRequest instanceof HttpServletResponse ){
//            HttpServletResponse alteredResponse = ((HttpServletResponse)servletResponse);
//            addCorsHeader(alteredResponse);
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
    }

//    private void addCorsHeader(HttpServletResponse response)
//    {
//        response.addHeader("ACCESS-CONTROL-ALLOW-ORIGIN", "http://localhost:3000/");
//        response.addHeader("ACCESS-CONTROL-ALLOW-METHOD", "GET, POST, PUT, OPTION");
//        response.addHeader("ACCESS-CONTROL-ALLOW-HEADER", "CONTENT-TYPE, Origin");
//    }

    @Override
    public void destroy() {

    }
}
