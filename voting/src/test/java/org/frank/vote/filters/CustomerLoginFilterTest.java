package org.frank.vote.filters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerLoginFilterTest {
    @Mock
    private HttpSession session;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private CustomerLoginFilter filter;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.setSession(session);
        filter.setAuthenticationManager(authenticationManager);
    }

    @Test
    void attemptAuthenticationWithCorrectCaptcha() {
        request.setMethod("POST");
        request.setParameter("captcha", "testCaptcha");
        when(session.getAttribute("captcha")).thenReturn("testCaptcha");
        assertDoesNotThrow(() -> filter.attemptAuthentication(request, response));
    }

    @Test
    void attemptAuthenticationWithIncorrectCaptcha() {
        request.setMethod("POST");
        request.setParameter("captcha", "wrongCaptcha");
        when(session.getAttribute("captcha")).thenReturn("testCaptcha");
        assertThrows(AuthenticationServiceException.class, () -> filter.attemptAuthentication(request, response));
    }

    @Test
    void attemptAuthenticationWithUnsupportedMethod() {
        request.setMethod("GET");
        assertThrows(AuthenticationServiceException.class, () -> filter.attemptAuthentication(request, response));
    }
    
}
