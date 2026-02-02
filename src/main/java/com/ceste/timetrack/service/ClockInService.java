package com.ceste.timetrack.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    public ClockInService(EmployeeRepository employeeRepository, ClockInRepository clockInRepository){
        this.employeeRepository = employeeRepository;
        this.clockInRepository = clockInRepository;
    }

    public CheckInRequestDTO checkIn(CheckInRequestDTO checkInDTO){
        Employee employee = this.employeeRepository.findById(checkInDTO.getIdEmployee())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Empleado no encontrado"
                ));

        if(!employee.isActive()){
            //ERROR. EMPLEADO NO ACTIVO
        }

        LocalDateTime today = LocalDateTime.now();
        boolean clockInAlreadySubmitted = this.clockInRepository.comprobarCheckIn(checkInDTO.getIdEmployee(), today);

        if(clockInAlreadySubmitted){
            //ERROR. Fichaje ya abierto
        }
        
        ClockIn clockIn = new ClockIn();
        clockIn.setEmployee(employee);
        clockIn.setCheckIn(today);
        clockIn.setOpened(true);
        clockIn.setNotes(null);

        clockInRepository.save(clockIn);

        return checkInDTO;
    }
}
