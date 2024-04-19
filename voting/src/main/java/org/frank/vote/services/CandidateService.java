package org.frank.vote.services;

import com.sun.istack.NotNull;
import org.frank.vote.daos.CandidateDao;
import org.frank.vote.entities.Candidate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CandidateService {
    
    private final CandidateDao candidateDao;
    
    public CandidateService(@NotNull CandidateDao candidateDao){
        this.candidateDao = candidateDao;
    }
    
    public List<Candidate> findAll(){
        return candidateDao.findAll();
    }

    @Transactional
    public void incrementVote(String candidateName) {
        candidateDao.incrementVoteCountByCandidateName(candidateName);
    }
}
