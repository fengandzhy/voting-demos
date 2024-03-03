package org.frank.vote.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="t_recorder")
public class Recorder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique=true)
    private String username;

    @Column(name = "candidate_name")
    private String candidateName;

    @Column(name = "vote_time")
    private LocalDateTime voteTime;

    public Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(@NotNull String username) {
        this.username = username;
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
