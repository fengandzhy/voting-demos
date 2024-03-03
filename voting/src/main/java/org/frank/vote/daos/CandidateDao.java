package org.frank.vote.daos;

import com.sun.istack.NotNull;
import org.frank.vote.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CandidateDao extends JpaRepository<Candidate,Long> {

    @Modifying
    @Query("UPDATE Candidate c SET c.voteCounting = c.voteCounting + 1 WHERE c.candidateName = :candidateName")
    void incrementVoteCountByCandidateName(@NotNull String candidateName);
}
