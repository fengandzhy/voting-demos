package org.frank.vote.daos;


import com.sun.istack.NotNull;
import org.frank.vote.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Long> {    
    Role findByName(@NotNull String name);
}
