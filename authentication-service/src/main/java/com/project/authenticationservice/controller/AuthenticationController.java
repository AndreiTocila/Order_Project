package com.project.authenticationservice.controller;

import com.project.authenticationservice.dto.UserLoginDTO;
import com.project.authenticationservice.dto.UserRegisterDTO;
import com.project.authenticationservice.service.KeycloakService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController
{

    private final KeycloakService keycloakService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDTO userDTO)
    {
        keycloakService.createUser(userDTO);
        return new ResponseEntity<>("User created succesfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userDTO)
    {
        String token = keycloakService.generateToken(userDTO);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
