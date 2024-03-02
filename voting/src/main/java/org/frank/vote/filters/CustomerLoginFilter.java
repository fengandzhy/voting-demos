package org.frank.vote.filters;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerLoginFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (request.getMethod().equals("POST")) {
            String captcha = request.getParameter("captcha");
            String sessionCaptcha = (String) request.getSession().getAttribute("captcha");
            if (!StringUtils.isEmpty(captcha) && !StringUtils.isEmpty(sessionCaptcha) && captcha.equalsIgnoreCase(sessionCaptcha)) {
                return super.attemptAuthentication(request, response);
            }
            throw new AuthenticationServiceException("The captcha has expired or the input is incorrect");
        }
        throw new AuthenticationServiceException("Authentication method not supported");
    }       
}
