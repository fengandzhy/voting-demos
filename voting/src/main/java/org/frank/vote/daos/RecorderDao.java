package org.frank.vote.daos;

import com.sun.istack.NotNull;
import org.frank.vote.entities.Recorder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecorderDao extends JpaRepository<Recorder,Long> {
    
    Optional<Recorder> findCandidateNameByUsername(@NotNull String username);
}
