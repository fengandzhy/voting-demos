package org.frank.vote.services;

import org.frank.vote.daos.RecorderDao;
import org.frank.vote.entities.Recorder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecorderServiceTest {

    @Mock
    private RecorderDao recorderDao;
    
    @InjectMocks
    private RecorderService recorderService;
    
    @Test
    void saveNewVoteRecordShouldCallDaoMethod(){
        Recorder recorder = mock(Recorder.class);
        recorderService.saveNewVoteRecord(recorder);
        verify(recorderDao, times(1)).save(recorder);
    }
    
    @Test
    void findCandidateNameByUsernameShouldCallDaoMethodAndReturnCorrectValue(){
        Recorder recorder = mock(Recorder.class);        
        when(recorderDao.findCandidateNameByUsername(any())).thenReturn(Optional.of(recorder));
        when(recorder.getCandidateName()).thenReturn("John");
        String name = recorderService.findCandidateNameByUsername("Smith");
        verify(recorderDao, times(1)).findCandidateNameByUsername("Smith");
        assertEquals("John", name);
    }
}
