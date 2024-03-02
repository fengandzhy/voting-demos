package org.frank.vote.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="t_recorder")
public class Recorder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "voter_name", unique=true)
    private String voterName;

    @Column(name = "candidate_name", unique=true)
    private String candidateName;

    @Column(name = "vote_time", unique=true)
    private LocalDateTime voteTime;

    public Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public String getVoterName() {
        return voterName;
    }

    public void setVoterName(@NotNull String voterName) {
        this.voterName = voterName;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(@NotNull String candidateName) {
        this.candidateName = candidateName;
    }

    public LocalDateTime getVoteTime() {
        return voteTime;
    }

    public void setVoteTime(@NotNull LocalDateTime voteTime) {
        this.voteTime = voteTime;
    }
}
