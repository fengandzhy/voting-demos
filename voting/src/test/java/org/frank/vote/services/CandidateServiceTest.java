package org.frank.vote.services;

import org.frank.vote.daos.CandidateDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CandidateServiceTest {

    @Mock
    private CandidateDao candidateDao;

    @InjectMocks
    private CandidateService candidateService;

    @Test
    void incrementVoteShouldCallDaoMethod() {        
        String candidateName = "John Doe";       
        candidateService.incrementVote(candidateName);        
        verify(candidateDao, times(1)).incrementVoteCountByCandidateName(candidateName);
    }
}
