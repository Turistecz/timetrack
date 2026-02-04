package com.ceste.timetrack.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ceste.timetrack.dto.LoginDTO;
import com.ceste.timetrack.dto.UserResponseDTO;
import com.ceste.timetrack.model.Employee;
import com.ceste.timetrack.repository.EmployeeRepository;


@Service
public class LoginService {

    private final EmployeeRepository employeeRepository;

    public LoginService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public ResponseEntity<UserResponseDTO> login(LoginDTO loginDTO) {
        
        Employee employee = this.employeeRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());

        if(employee == null){
            throw new ResponseStatusException(
                 HttpStatus.UNAUTHORIZED,"Empleado no puede ser nulo");   
        }

        if(!employee.isActive()) {
            throw new ResponseStatusException(
                HttpStatus.FORBIDDEN, "Empleado inactivo, no puede acceder");
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setName(employee.getName());
        userResponseDTO.setEmail(employee.getEmail());
        userResponseDTO.setRol(employee.getRol());

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);
  
    }
    
}