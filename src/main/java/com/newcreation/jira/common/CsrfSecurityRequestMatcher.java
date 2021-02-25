package com.newcreation.jira.common;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class CsrfSecurityRequestMatcher implements RequestMatcher{

    private Pattern allowedMethods = Pattern.compile("^(GET)$");
    private RegexRequestMatcher unprotectedLogoutMatcher = new RegexRequestMatcher("/logout", null);

    @Override
    public boolean matches(HttpServletRequest request) {
        if(allowedMethods.matcher(request.getMethod()).matches()){
            return false;
        }

        if("mobile".equals(request.getHeader("X-Requested-With"))) {
            return false;
        }

        return !(unprotectedLogoutMatcher.matches(request));
    }

}
