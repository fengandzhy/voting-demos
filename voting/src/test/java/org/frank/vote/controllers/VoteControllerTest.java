package org.frank.vote.controllers;

import org.frank.vote.entities.User;
import org.frank.vote.services.CandidateService;
import org.frank.vote.services.RecorderService;
import org.frank.vote.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VoteControllerTest {

    @Mock
    private CandidateService candidateService;

    @Mock
    private RecorderService recorderService;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @InjectMocks
    private VoteController voteController;

    private User mockUser;

    @BeforeEach
    void setUp() {
        mockUser = new User();
        mockUser.setUsername("user1");
        mockUser.setVoted(false);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(mockUser, null, mockUser.getAuthorities());
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }
    
    @Test
    public void vote_ShouldReturnSuccessPage(){
        doNothing().when(userService).updateUserVoteStatus(anyString());
        doNothing().when(candidateService).incrementVote(anyString());
        doNothing().when(recorderService).saveNewVoteRecord(any());
        String viewName = voteController.vote("ACD", model);
        assertEquals("/pages/success", viewName);        
    }
}
