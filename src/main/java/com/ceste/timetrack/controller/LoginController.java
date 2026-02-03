package com.ceste.timetrack.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceste.timetrack.dto.LoginDTO;
import com.ceste.timetrack.dto.UserResponseDTO;
import com.ceste.timetrack.service.LoginService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class LoginController {
    

    private final LoginService loginService;

    public LoginController (LoginService loginService) {
        this.loginService = loginService;
    };

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@Valid @RequestBody LoginDTO user){
        
        return this.loginService.login(user);
    };
}
