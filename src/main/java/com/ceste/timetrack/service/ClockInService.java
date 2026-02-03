package com.ceste.timetrack.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ceste.timetrack.dto.CheckInRequestDTO;
import com.ceste.timetrack.dto.CheckInResponseDTO;
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

    public ResponseEntity<CheckInResponseDTO> checkIn(CheckInRequestDTO checkInDTO){
        Employee employee = this.employeeRepository.findById(checkInDTO.getIdEmployee())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Empleado no encontrado"
                ));

        if(!employee.isActive()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "El empleado no está activo");
        }

        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay();

        boolean clockInAlreadySubmitted = this.clockInRepository.comprobarCheckIn(checkInDTO.getIdEmployee(), start, end);

        if(clockInAlreadySubmitted){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya han un fichaje abierto para este empleado");
        }
        
        LocalDateTime dateCheckIn = LocalDateTime.now();
        ClockIn clockIn = new ClockIn();
        clockIn.setEmployee(employee);
        clockIn.setCheckIn(dateCheckIn);
        clockIn.setOpened(true);
        clockIn.setNotes(null);
        clockInRepository.save(clockIn);


        ClockIn currentCheckIn = this.clockInRepository.obtenerCheckIn(checkInDTO.getIdEmployee(), start, end);        
        CheckInResponseDTO responseDTO = new CheckInResponseDTO();
        responseDTO.setIdClockIn(currentCheckIn.getId());
        responseDTO.setIdEmployee(currentCheckIn.getEmployee().getId());
        responseDTO.setCheckIn(currentCheckIn.getCheckIn());
        responseDTO.setNotes(currentCheckIn.getNotes());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
