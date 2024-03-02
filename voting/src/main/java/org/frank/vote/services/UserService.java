package org.frank.vote.services;

import com.sun.istack.NotNull;
import org.frank.vote.daos.RoleDao;
import org.frank.vote.daos.UserDao;
import org.frank.vote.entities.Role;
import org.frank.vote.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {

    private UserDao userDao;
    private RoleDao roleDao;

    public UserService(@NotNull UserDao userDao, @NotNull RoleDao roleDao){
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public UserDetails loadUserByUsername(@NotNull String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(null == user){
            throw new UsernameNotFoundException("User does not exist!");
        }
        return user;
    }
    
    @Transactional
    public void addUserAndRole(@NotNull User user, @NotNull String roleName) {
        Role role = roleDao.findByName(roleName);
        user.getRoles().add(role);
        userDao.save(user);
    }

    @Transactional
    public void deleteUserByUsername(@NotNull String username) {
        userDao.deleteByUsername(username);
    }
}
