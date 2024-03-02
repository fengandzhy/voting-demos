package org.frank.vote.daos;

import org.frank.vote.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateDao extends JpaRepository<Candidate,Long> {
    
}
