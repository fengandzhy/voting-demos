package org.frank.vote.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity(name="t_candidate")
public class Candidate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "candidate_name", unique=true)
    private String candidateName;

    @Column(name = "candidate_desc")
    private String candidateDesc;

    @Column(name = "vote_counting")
    private int voteCounting;

    public Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(@NotNull String candidateName) {
        this.candidateName = candidateName;
    }

    public String getCandidateDesc() {
        return candidateDesc;
    }

    public void setCandidateDesc(@NotNull String candidateDesc) {
        this.candidateDesc = candidateDesc;
    }

    public int getVoteCounting() {
        return voteCounting;
    }

    public void setVoteCounting(int voteCounting) {
        this.voteCounting = voteCounting;
    }
}
