package com.ceste.timetrack.service;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ceste.timetrack.dto.RegisterRequestDTO;
import com.ceste.timetrack.model.Employee;
import com.ceste.timetrack.model.EnumRol;
import com.ceste.timetrack.repository.EmployeeRepository;


@Service
public class RegisterService {

   private final EmployeeRepository employeeRepository;

   public RegisterService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    

    public ResponseEntity<String> registerEmployee(RegisterRequestDTO registerDTO){

        if (employeeRepository.existsByEmail(registerDTO.getEmail())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un empleado con ese email");
        }

        if (registerDTO.getRol() != EnumRol.ADMIN && registerDTO.getRol() != EnumRol.EMPLEADO) {
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST,"Rol no existe");
        }

      Employee employee = new Employee();
        employee.setName(registerDTO.getName());
        employee.setEmail(registerDTO.getEmail());
        employee.setPassword(registerDTO.getPassword());
        employee.setRol(registerDTO.getRol());
        employee.setActive(registerDTO.isActive());
        employee.setCreated_at(LocalDateTime.now());

        employeeRepository.save(employee);

    return ResponseEntity.status(HttpStatus.CREATED).body("Empleado registrado correctamente");

    }
}
