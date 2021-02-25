package com.newcreation.jira.common;

import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SameSiteStrictCookie extends GenericFilterBean {

    @Override
    public void doFilter(
            ServletRequest request, ServletResponse response, FilterChain chain
    ) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        String cookie = resp.getHeader(HttpHeaders.SET_COOKIE);
        if (cookie != null) {
            resp.setHeader(HttpHeaders.SET_COOKIE, cookie + "; SameSite=strict");
        }
        chain.doFilter(request, response);
    }
}