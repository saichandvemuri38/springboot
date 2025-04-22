package com.newsletter.auth_service.service;

import com.newsletter.auth_service.components.CustomUserDetails;
import com.newsletter.auth_service.enity.UserEntity;
import com.newsletter.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findUserEntityByEmail(username);
        if (Objects.isNull(user)) {
            System.out.println("User not found");
            throw new UsernameNotFoundException(username);
        }else{
            return new CustomUserDetails(user);
        }
    }
}
