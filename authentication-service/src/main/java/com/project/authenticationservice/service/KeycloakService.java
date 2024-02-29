package com.project.authenticationservice.service;

import com.project.authenticationservice.dto.UserLoginDTO;
import com.project.authenticationservice.dto.UserRegisterDTO;

public interface KeycloakService
{
    void createUser(UserRegisterDTO userDTO);

    String generateToken(UserLoginDTO userDTO);
}
