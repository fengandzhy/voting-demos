package org.frank.vote.services;

import org.frank.vote.daos.UserDao;
import org.frank.vote.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private UserDao userDao;

    public UserService(UserDao userDao){
        this.userDao = userDao;        
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(null == user){
            throw new UsernameNotFoundException("User does not exist!");
        }
        return user;
    }    
}
