package com.ceste.timetrack.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ceste.timetrack.dto.RegisterRequestDTO;
import com.ceste.timetrack.service.RegisterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController (RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerEmployee(@Valid @RequestBody RegisterRequestDTO employee){

        return this.registerService.registerEmployee(employee);
    }
    
}
