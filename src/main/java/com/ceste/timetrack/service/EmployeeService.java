package com.ceste.timetrack.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ceste.timetrack.dto.GetUserByIdRequestDTO;
import com.ceste.timetrack.dto.GetUserByIdResponseDTO;
import com.ceste.timetrack.model.Employee;
import com.ceste.timetrack.repository.EmployeeRepository;

@Service
public class EmployeeService {
    
    // Contstructor para inyectar el repositorio
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    
    public ResponseEntity<GetUserByIdResponseDTO> getUserById(GetUserByIdRequestDTO reqDTO){
        
        // Comprobar si existe el empleado, buscándolo por id
        Employee employee = this.employeeRepository.findById(reqDTO.getIdEmployee())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Empleado no encontrado"
                ));

        // Comprobar que el empleado está activo
        if(!employee.isActive()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "El empleado no está activo");
        }

        // Asignar datos de respuesta
        GetUserByIdResponseDTO respDTO = new GetUserByIdResponseDTO();
        respDTO.setIdEmployee(employee.getId());
        respDTO.setName(employee.getName());
        respDTO.setEmail(employee.getEmail());
        respDTO.setPassword(employee.getPassword());
        respDTO.setRol(employee.getRol());
        respDTO.setActive(employee.isActive());

        return ResponseEntity.status(HttpStatus.OK).body(respDTO);
    }

    
}
