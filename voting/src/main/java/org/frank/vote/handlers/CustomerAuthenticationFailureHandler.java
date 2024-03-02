package org.frank.vote.handlers;

import com.sun.istack.NotNull;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomerAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(@NotNull HttpServletRequest request, 
                                        @NotNull HttpServletResponse response, 
                                        @NotNull AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = exception.getMessage();
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("/login.html").forward(request,response);        
    }
}
