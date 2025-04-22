package com.newsletter.auth_service.service;

import com.newsletter.auth_service.enity.UserEntity;
import com.newsletter.auth_service.interfaces.UserInterface;
import com.newsletter.auth_service.models.LoginModel;
import com.newsletter.auth_service.models.LoginResponseDTO;
import com.newsletter.auth_service.models.UserModel;
import com.newsletter.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService implements UserInterface {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Override
    public String register(UserModel user) {
        var returnedUser = userRepository.findUserEntityByEmail(user.getEmail());
        if (Objects.isNull(returnedUser)) {
            UserEntity userEntity = new UserEntity();
            userEntity.setFirstname(user.getFirstname());
            userEntity.setLastname(user.getLastname());
            userEntity.setEmail(user.getEmail());
            userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
            userEntity.setDob(user.getDob());
            userEntity.setPhone(user.getPhone());
            System.out.println(userEntity.getFirstname());
            var registereduser =  userRepository.save(userEntity);
            LoginModel loginModel = new LoginModel();
            loginModel.setEmail(registereduser.getEmail());
            loginModel.setPassword(registereduser.getPassword());
            return jwtService.generateToken(loginModel);
        } else {
            return "error";
        }
    }

    @Override
    public String login(LoginModel loginModel) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginModel.getEmail(), loginModel.getPassword()
                )
        );
        if (authenticate.isAuthenticated()) {
            String token =  jwtService.generateToken(loginModel);
            System.out.println(token);
            return token;
        }
        System.out.println("failure");
        return "error";
    }

    @Override
    public boolean validateToken(String token) {
        return jwtService.validateToken(token);
    }
}
