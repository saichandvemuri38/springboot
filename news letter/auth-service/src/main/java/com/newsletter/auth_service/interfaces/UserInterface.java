package com.newsletter.auth_service.interfaces;

import com.newsletter.auth_service.enity.UserEntity;
import com.newsletter.auth_service.models.LoginModel;
import com.newsletter.auth_service.models.LoginResponseDTO;
import com.newsletter.auth_service.models.UserModel;

public interface UserInterface {
    String register(UserModel user);

    String login(LoginModel loginModel);

    boolean validateToken(String token);
}
