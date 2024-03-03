package org.frank.vote.services;

import com.sun.istack.NotNull;
import org.frank.vote.daos.CandidateDao;
import org.frank.vote.entities.Candidate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {
    
    private CandidateDao candidateDao;
    
    public CandidateService(@NotNull CandidateDao candidateDao){
        this.candidateDao = candidateDao;
    }
    
    public List<Candidate> findAll(){
        return candidateDao.findAll();
    }
}
