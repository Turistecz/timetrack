package com.ceste.timetrack.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ceste.timetrack.dto.GetUserByIdRequestDTO;
import com.ceste.timetrack.dto.GetUserByIdResponseDTO;
import com.ceste.timetrack.service.EmployeeService;
import jakarta.validation.Valid;

@RestController
public class EmployeeController {

    // Constructor para injectar el servicio
    private final EmployeeService employeeService;
    public EmployeeController (EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    // Metodo para recuperar datos de un usuario con su id
    @GetMapping("/getUserById")
    public ResponseEntity<GetUserByIdResponseDTO> getUserById(@Valid @RequestBody GetUserByIdRequestDTO reqDTO){
        return this.employeeService.getUserById(reqDTO);
    }
}
