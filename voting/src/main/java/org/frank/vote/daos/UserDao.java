package org.frank.vote.daos;


import com.sun.istack.NotNull;
import org.frank.vote.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
    User findByUsername(@NotNull String username);
    void deleteByUsername(@NotNull String username);
}
