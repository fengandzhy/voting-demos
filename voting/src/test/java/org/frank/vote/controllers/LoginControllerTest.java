package org.frank.vote.controllers;

import com.google.code.kaptcha.Producer;
import org.frank.vote.entities.Candidate;
import org.frank.vote.entities.User;
import org.frank.vote.services.CandidateService;
import org.frank.vote.services.RecorderService;
import org.frank.vote.util.ImageIOWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    @Mock
    private RecorderService recorderService;
    
    @Mock
    private Producer kaptCha;

    @Mock
    private CandidateService candidateService;

    @Mock
    private Model model;

    @Mock
    private ImageIOWrapper imageIOWrapper;

    @InjectMocks
    private LoginController loginController;

    private HttpSession session;
    private MockHttpServletResponse response;

    private User mockUser;

    @BeforeEach
    void setUp() {
        session = new MockHttpSession();
        response = new MockHttpServletResponse();        
    }

    @Test
    public void votePage_WhenUserHasNotVoted_ShouldReturnVotePage() {
        votePageSetUp();
        List<Candidate> candidateList = new ArrayList<>();
        when(candidateService.findAll()).thenReturn(candidateList);
        String viewName = loginController.votePage(model);
        assertEquals("/pages/vote", viewName);
    }

    @Test
    public void votePage_WhenUserHasVoted_ShouldReturnSuccessPage() {
        votePageSetUp();
        mockUser.setVoted(true);
        when(recorderService.findCandidateNameByUsername(anyString())).thenReturn("ABC");
        String viewName = loginController.votePage(model);
        assertEquals("/pages/success", viewName);
    }

    @Test
    void getVerifyCode_GenerateImage_SetCaptchaSession() throws IOException {
        BufferedImage mockedImage = mock(BufferedImage.class);
        when(kaptCha.createImage(anyString())).thenReturn(mockedImage);
        when(kaptCha.createText()).thenReturn("5224");
        doNothing().when(imageIOWrapper).writeImage(any(BufferedImage.class), anyString(), any(OutputStream.class));
        loginController.getVerifyCode(response, session);
        assertNotNull(session.getAttribute("captcha"));
        assertEquals("image/jpeg", response.getContentType());        
    }
    
    private void votePageSetUp(){
        mockUser = new User();
        mockUser.setUsername("user1");
        mockUser.setVoted(false);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(mockUser, null, mockUser.getAuthorities());
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }
}
