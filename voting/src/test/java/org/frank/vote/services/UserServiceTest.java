package org.frank.vote.services;

import org.frank.vote.daos.RoleDao;
import org.frank.vote.daos.UserDao;
import org.frank.vote.entities.Role;
import org.frank.vote.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserDao userDao;
    
    @Mock
    private RoleDao roleDao;

    @InjectMocks
    private UserService userService;

    @Test
    void loadUserByUsername_UserExists_ReturnsUser() {        
        User mockUser = new User(); 
        mockUser.setUsername("existingUser");
        when(userDao.findByUsername("existingUser")).thenReturn(mockUser);        
        UserDetails result = userService.loadUserByUsername("existingUser");        
        assertNotNull(result, "UserDetails should not be null");
        assertEquals("existingUser", result.getUsername(), "Username should match");
    }

    @Test
    void loadUserByUsername_UserDoesNotExist_ThrowsException() {        
        when(userDao.findByUsername("nonExistingUser")).thenReturn(null);        
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("nonExistingUser"),
                "UsernameNotFoundException should be thrown");
    }

    @Test
    void loadUserByUsernameShouldCallDaoMethod(){
        Role role = mock(Role.class);
        when(roleDao.findByName(anyString())).thenReturn(role);
        User user = mock(User.class);
        userService.addUserAndRole(user,"role");
        verify(roleDao, times(1)).findByName("role");
        verify(userDao, times(1)).save(user);
    }
}
