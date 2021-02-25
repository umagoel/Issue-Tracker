package com.newcreation.jira.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
//        String captcha = request.getParameter("captchaResponse") == null ? "" : request.getParameter("captchaResponse");
//        if (ConfigUtils.CLIENT_CONFIG.isCaptchaEnabled()) {
//            if(loginAttemptService.enableCaptcha(request.getParameter("username").toLowerCase()) == CaptchaValidator.validate(captcha)){
//                return super.attemptAuthentication(request, response);
//            }
//            throw new InvalidCaptchaException();
//        }

        return super.attemptAuthentication(request, response);
    }

}