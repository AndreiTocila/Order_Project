package com.project.authenticationservice.dto;

import lombok.Data;

@Data
public class UserRegisterDTO
{
    private String username;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

}
