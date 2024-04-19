package org.frank.vote.daos;


import com.sun.istack.NotNull;
import org.frank.vote.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User,Long> {
    User findByUsername(@NotNull String username);
    void deleteByUsername(@NotNull String username);

    @Modifying
    @Query("UPDATE User u SET u.isVoted = 1 WHERE u.username = :username")
    void updateUserVoteStatus(@NotNull String username);
    
}
