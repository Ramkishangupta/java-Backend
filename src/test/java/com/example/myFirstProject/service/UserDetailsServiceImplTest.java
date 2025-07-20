package com.example.myFirstProject.service;

import com.example.myFirstProject.Repository.UserEntryRepository;
import com.example.myFirstProject.entry.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTest {

    @InjectMocks
    private CustomUserDetailServiceImpl userDetailService;

    @Mock
    private UserEntryRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsernameTest() {
        // Arrange
        User mockUser = User.builder()
                .userName("ram")
                .password("ncdkjsac")
                .userRoles(Collections.emptyList())
                .build();

        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(mockUser);

        UserDetails user = userDetailService.loadUserByUsername("ram");

        assertNotNull(user);
    }
}
