package com.example.Security.service;

import com.example.Security.entity.User;
import com.example.Security.enums.Role;
import com.example.Security.model.AuthenticationResponse;
import com.example.Security.model.RegisterRequest;
import com.example.Security.repository.UserRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AuthServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthService authService;

    @DisplayName("Register User")
    @Test
    public void test_registerUser() {
        // Arrange
        RegisterRequest registerRequest = getRegisterRequest();
        User user = getUser();
        String encodedPassword = "encodedPassword";
        String jwtToken = "jwtToken";

        when(passwordEncoder.encode(registerRequest.getPassword())).thenReturn(encodedPassword);
        when(userRepo.save(any(User.class))).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn(jwtToken);

        // Act
        AuthenticationResponse response = authService.register(registerRequest);

        // Assert
        assertNotNull(response);
        assertEquals(jwtToken, response.getToken());
        verify(passwordEncoder).encode(registerRequest.getPassword());
        verify(userRepo).save(any(User.class));
        verify(jwtService).generateToken(user);
    }

    private RegisterRequest getRegisterRequest() {
        return RegisterRequest.builder()
                .email("email@email.com")
                .password("password")
                .name("name")
                .role(Role.ADMIN)
                .build();
    }

    private User getUser() {
        return User.builder()
                .email("email@email.com")
                .password("encodedPassword")
                .name("name")
                .role(Role.ADMIN)
                .build();
    }
}
