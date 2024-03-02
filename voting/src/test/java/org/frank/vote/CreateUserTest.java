package org.frank.vote;

import org.frank.vote.daos.RoleDao;
import org.frank.vote.daos.UserDao;
import org.frank.vote.entities.User;
import org.frank.vote.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CreateUserTest {
    
    private static final String USER_NAME = "Frank";
    private static final String ADMIN_NAME = "Sam";

    @Autowired
    UserService userService;
    
    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void createCommonUser(){
        assertDoesNotThrow(() -> {
            userService.deleteUserByUsername(USER_NAME);
            User u1 = new User();
            u1.setUsername(USER_NAME);
            u1.setUserNumber("000000000001");
            u1.setPassword(passwordEncoder.encode("123456"));
            u1.setAccountNonExpired(true);
            u1.setAccountNonLocked(true);
            u1.setCredentialsNonExpired(true);
            u1.setEnabled(true);
            userService.addUserAndRole(u1,"ROLE_user");
        }, "Test Failure");       
    }

    @Test
    public void createCommonUserWithSameName_thenThrowException(){
        assertThrows(DataIntegrityViolationException.class, () -> {
            User u1 = new User();
            u1.setUsername(USER_NAME);
            u1.setUserNumber("000000000001");
            u1.setPassword(passwordEncoder.encode("123456"));
            u1.setAccountNonExpired(true);
            u1.setAccountNonLocked(true);
            u1.setCredentialsNonExpired(true);
            u1.setEnabled(true);            
            userService.addUserAndRole(u1,"ROLE_user");
            userService.addUserAndRole(u1,"ROLE_user"); // create the user twice if the user dose not exist. 
        });        
    }

    @Test
    public void createAdminUser(){
        assertDoesNotThrow(() -> {
            userService.deleteUserByUsername(ADMIN_NAME);
            User u1 = new User();
            u1.setUsername(ADMIN_NAME);
            u1.setUserNumber("000000000002");
            u1.setPassword(passwordEncoder.encode("123456"));
            u1.setAccountNonExpired(true);
            u1.setAccountNonLocked(true);
            u1.setCredentialsNonExpired(true);
            u1.setEnabled(true);
            userService.addUserAndRole(u1,"ROLE_admin");
        }, "Test Failure");
    }
}
