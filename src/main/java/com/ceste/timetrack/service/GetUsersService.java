package com.ceste.timetrack.service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.ceste.timetrack.model.Employee;
import com.ceste.timetrack.repository.EmployeeRepository;

@Service
public class GetUsersService {

    private final EmployeeRepository employeeRepository;

    public GetUsersService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getUsers() {
        
        List<Employee> employees = employeeRepository.findAll();

        if (employees.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No hay empleados"
            );
        }
        return employees;
    }
}

    

