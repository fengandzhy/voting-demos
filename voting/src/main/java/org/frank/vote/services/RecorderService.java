package org.frank.vote.services;

import com.sun.istack.NotNull;
import org.frank.vote.daos.RecorderDao;
import org.frank.vote.entities.Recorder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RecorderService {
    
    private RecorderDao recorderDao;
    
    public RecorderService(@NotNull RecorderDao recorderDao){
        this.recorderDao = recorderDao;
    }
    
    @Transactional
    public void saveNewVoteRecord(@NotNull Recorder recorder){
        recorderDao.save(recorder);
    }
}
