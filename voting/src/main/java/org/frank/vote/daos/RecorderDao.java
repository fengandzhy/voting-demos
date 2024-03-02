package org.frank.vote.daos;

import org.frank.vote.entities.Recorder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecorderDao extends JpaRepository<Recorder,Long> {
}
