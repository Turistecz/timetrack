package com.ceste.timetrack.service;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ceste.timetrack.dto.CheckInRequestDTO;
import com.ceste.timetrack.model.ClockIn;
import com.ceste.timetrack.model.Employee;
import com.ceste.timetrack.repository.ClockInRepository;
import com.ceste.timetrack.repository.EmployeeRepository;


@Service
public class ClockInService {
    
    private final EmployeeRepository employeeRepository;
    private final ClockInRepository clockInRepository;

    public ClockInService(EmployeeRepository employeeRepository, ClockInRepository clockInRepository) {
        this.employeeRepository = employeeRepository;
        this.clockInRepository = clockInRepository;


    }

    //¿Cuántas validaciones voy a necesitar?

    public CheckInRequestDTO checkIn(CheckInRequestDTO checkInDTO) {

        // Buscar el empleado por ID
        Employee employee = this.employeeRepository.findById(checkInDTO.getIdEmpleado())
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Empleado no encontrado"));

        // Comprobar si está activo
        if(!employee.isActive()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Empleado inactivo, no puede registrar entrada");
        }

        //Para comporbar si ya hizo checkIn hoy y la fecha no es previa.
        LocalDateTime today = LocalDateTime.now();

        boolean clockInAlreadySubmitted = this.clockInRepository.validCheckIn(checkInDTO.getIdEmpleado(), today);

        if(clockInAlreadySubmitted){
            //ERROR. Fichaje yya abierto
        }

        // Inserciones a la BBDD
        // Una vez pasados las validaciones creamos la instancia de Objeto para insertarlo en la BBDD
        ClockIn clockIn = new ClockIn();
        clockIn.setEmployee(employee);
        clockIn.setCheckIn(today);
        clockIn.setOpened(true);
        clockIn.setNotes(null);

        // Guardamos el fichaje (save método JPA (que extiende de CRUD REPOSITORY) para insertar datos)
        clockInRepository.save(clockIn);

        return checkInDTO; 
           
    }

}